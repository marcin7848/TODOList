package TODOList.controllers;

import TODOList.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import TODOList.models.Account;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/loginProcess")
    @ResponseBody
    public String loginProcess(Model m, HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("Account") Account loginInfo) {

        Account account = accountService.validateAccount(loginInfo);

        String result;
        if (account == null)
            return "{\"error\":1, \"errorTitle\":\"Wrong password!\"," +
                    " \"errorDescription\":\"Username/password is not correct!\"}";

        if(account.getActivated() == 0)
            return "{\"error\":1, \"errorTitle\":\"Not activated account!\", " +
                    "\"errorDescription\":\"You have to activate you account! Check your email or resend activation email!\"}";

        Cookie user = new Cookie("username", account.getUsername());
        user.setMaxAge(900);
        user.setPath("/");
        user.setHttpOnly(true);
        response.addCookie(user);

        Cookie pass = new Cookie("password", account.getPassword());
        pass.setMaxAge(900);
        pass.setPath("/");
        pass.setHttpOnly(true);
        response.addCookie(pass);

        return "{\"error\":0}";

    }

    @PostMapping("/registerProcess")
    @ResponseBody
    public String registerProcess(HttpServletResponse response,
                                  @CookieValue(value = "username", required = false) String userCookie,
                                  @CookieValue(value = "password", required = false) String passCookie,
                                  @ModelAttribute("Account") Account registerInfo) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/"; //already logged
        } else {
            int result = accountService.registerAccount(registerInfo);
            if (result == 2)
                return "{\"error\":1, \"errorTitle\":\"Account exists!\"," +
                        " \"errorDescription\":\"This account has already existed! Try to login.\"}";


            return "{\"error\":0}";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletResponse response,
                         @CookieValue(value = "username", required = false) String userCookie,
                         @CookieValue(value = "password", required = false) String passCookie) {

        AccountService.deleteCookies(response);
        return "redirect:/";
    }

    @GetMapping("/activate")
    public String activateView(HttpServletResponse response,
                               @CookieValue(value = "username", required = false) String userCookie,
                               @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/";
        } else {
            AccountService.deleteCookies(response);
            return "activate";
        }
    }


    @PostMapping("/activate")
    @ResponseBody
    public String activateProcess(HttpServletResponse response,
                                  @CookieValue(value = "username", required = false) String userCookie,
                                  @CookieValue(value = "password", required = false) String passCookie,
                                  @ModelAttribute("activationCode") String activationCode) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/";
        } else {
            if (accountService.activateAccount(activationCode))
                return "{\"error\":0}";
            else
                return "{\"error\":1, \"errorTitle\":\"Bad code!\"," +
                        " \"errorDescription\":\"Bad activation code or account has already activated!\"}"; //bad code or account has already activated
        }
    }

    @PostMapping("/editAccount")
    @ResponseBody
    public String editProcess(HttpServletResponse response,
                              @CookieValue(value = "username", required = false) String userCookie,
                              @CookieValue(value = "password", required = false) String passCookie,
                              @ModelAttribute("Account") Account editAccount,
                              @ModelAttribute("oldPassword") String oldPassword) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if (account == null)
            return "redirect:/"; //please log in

        if(!accountService.compareAccountPassword(oldPassword, account.getPassword()))
            return "{\"error\":1, \"errorTitle\":\"Bad old password!\"," +
                    " \"errorDescription\":\"To change settings you have to give correct old password!\"}";


        int result = accountService.editAccount(account, editAccount.getFirstName(), editAccount.getSecondName(),
                editAccount.getPassword(), editAccount.getEmail());

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Email exist!\"," +
                    " \"errorDescription\":\"This email exists in database! Choose another email!\"}";

        Cookie pass = new Cookie("password", accountService.getAccount(account.getId()).getPassword());
        pass.setMaxAge(2000);
        pass.setPath("/");
        pass.setHttpOnly(true);
        response.addCookie(pass);

        return "{\"error\":0}"; //account edited
    }

    @GetMapping("/resendActivateCode")
    public String resendActivateCodeView(HttpServletResponse response,
                                         @CookieValue(value = "username", required = false) String userCookie,
                                         @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/"; //cannot when log in
        } else {
            return "resendActivateCode";
        }

    }

    @PostMapping("/resendActivateCode")
    @ResponseBody
    public String resendActivateCode(HttpServletResponse response,
                                     @CookieValue(value = "username", required = false) String userCookie,
                                     @CookieValue(value = "password", required = false) String passCookie,
                                     @ModelAttribute("email") String email) {


        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/"; //cannot when log in
        }

        if (accountService.resendActivateCode(email))
            return "{\"error\":0}"; //mail sent
        else
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"This account does not exist or is activated!\"}"; //this account don't exist or is activated

    }

    @GetMapping("/sendResetPassword")
    public String sendResetPasswordCodeView(HttpServletResponse response,
                                            @CookieValue(value = "username", required = false) String userCookie,
                                            @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/"; //cannot when log in
        } else {
            return "sendResetPassword";
        }

    }

    @PostMapping("/sendResetPassword")
    @ResponseBody
    public String sendResetPasswordCode(HttpServletResponse response,
                                        HttpServletRequest request,
                                        @CookieValue(value = "username", required = false) String userCookie,
                                        @CookieValue(value = "password", required = false) String passCookie,
                                        @ModelAttribute("email") String email) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/"; //cannot when log in
        }

        if (accountService.sendResetPassword(email, request.getLocalName()))
            return "{\"error\":0}"; //mail sent
        else
            return "{\"error\":1, \"errorTitle\":\"Bad email!\"," +
                    " \"errorDescription\":\"This email does not exist!\"}"; //that email doesn't exist

    }



    @GetMapping("/resetPassword/{code}")
    public String resetPasswordCheck(HttpServletResponse response,
                                     Model m,
                                     @CookieValue(value = "username", required = false) String userCookie,
                                     @CookieValue(value = "password", required = false) String passCookie,
                                     @PathVariable("code") String code) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/"; //cannot when log in
        } else {
            if (accountService.checkResetPasswordCode(code)) {
                m.addAttribute("code", code);
                return "resetPassword";
            }
            else {
                return "resetPasswordBadCode"; //this code doesn't exist
            }
        }

    }

    @PostMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(HttpServletResponse response,
                                HttpServletRequest request,
                                @CookieValue(value = "username", required = false) String userCookie,
                                @CookieValue(value = "password", required = false) String passCookie,
                                @ModelAttribute("code") String code,
                                @ModelAttribute("password") String password) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/"; //cannot when log in
        }

        if (accountService.resetPassword(code, password))
            return "{\"error\":0}"; //password reset
        else
            return "{\"error\":1, \"errorTitle\":\"Bad code!\"," +
                    " \"errorDescription\":\"Bad code! Unauthorised request!\"}"; //code doesn't exist

    }


}
