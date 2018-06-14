package TODOList.controllers;

import TODOList.services.AccountService;
import org.apache.commons.text.StringEscapeUtils;
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

    @GetMapping("/login")
    public String loginView(HttpServletResponse response,
                            @CookieValue(value = "username", required = false) String userCookie,
                            @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/";
        } else {
            AccountService.deleteCookies(response);
            return "login";
        }

    }

    @PostMapping("/loginProcess")
    public String loginProcess(Model m, HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("Account") Account loginInfo) {

        Account account = accountService.validateAccount(loginInfo);

        String result;
        if (account == null)
            result = "Wrong password!";
        else {
            if(account.getActivated() == 1) {
                result = account.getUsername();
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
            }else{
                result = "Aktywuj swoje konto!";
            }
        }


        m.addAttribute("someAttribute", result);
        return "index";
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
    public String activateProcess(HttpServletResponse response,
                               @CookieValue(value = "username", required = false) String userCookie,
                               @CookieValue(value = "password", required = false) String passCookie,
                                  @ModelAttribute("activateCode") String activateCode) {

        activateCode = StringEscapeUtils.escapeJava(activateCode);

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/";
        } else {
            if(accountService.activateAccount(activateCode))
                return "redirect:/login";
            else
                return "activate"; //bad code or account has already activated
        }
    }

    @GetMapping("/register")
    public String registerView(HttpServletResponse response,
                               @CookieValue(value = "username", required = false) String userCookie,
                               @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/";
        } else {
            AccountService.deleteCookies(response);
            return "register";
        }
    }

    @PostMapping("/registerProcess")
    public String registerProcess(HttpServletResponse response,
                               @CookieValue(value = "username", required = false) String userCookie,
                               @CookieValue(value = "password", required = false) String passCookie,
                                  @ModelAttribute("Account") Account registerInfo) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/";
        } else {
            int result = accountService.registerAccount(registerInfo);
            if(result == 1)
                return "redirect:/activate"; //created, go activate

            if(result == 2)
                return "redirect:/"; //account's already existed

            return "redirect:/"; //unknown error
        }
    }

    @GetMapping("/editAccount")
    public String editView(HttpServletResponse response,
                               @CookieValue(value = "username", required = false) String userCookie,
                               @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "editAccount";
        } else {
            AccountService.deleteCookies(response);
            return "redirect:/";
        }

    }

    @PostMapping("/editAccount")
    public String editProcess(HttpServletResponse response,
                                  @CookieValue(value = "username", required = false) String userCookie,
                                  @CookieValue(value = "password", required = false) String passCookie,
                                  @ModelAttribute("Account") Account editAccount) {

        if (!AccountService.validateCookies(new Account(userCookie, passCookie))) {
            AccountService.deleteCookies(response);
            return "redirect:/"; //please log in
        }

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        accountService.editAccount(account, editAccount.getFirstName(), editAccount.getSecondName(),
                editAccount.getPassword(), editAccount.getEmail());

        Cookie pass = new Cookie("password", accountService.getAccount(account.getId()).getPassword());
        pass.setMaxAge(900);
        pass.setPath("/");
        pass.setHttpOnly(true);
        response.addCookie(pass);

        return "redirect:/"; //list created
    }

}
