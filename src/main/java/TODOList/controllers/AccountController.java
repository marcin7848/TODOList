package TODOList.controllers;

import TODOList.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import TODOList.models.Account;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController {

    @Autowired
    AccountDao accountDao;

    @GetMapping("/login")
    public String loginView(HttpServletResponse response,
                            @CookieValue(value = "username", required = false) String userCookie,
                            @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountDao.validateCookies(new Account(userCookie, passCookie))) {
            return "redirect:/";
        } else {
            AccountDao.deleteCookies(response);
            return "login";
        }

    }


    @PostMapping("/loginProcess")
    public String loginProcess(Model m, HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("Account") Account login) {

        Account account = accountDao.validateAccount(login);

        String result;
        if (account == null)
            result = "Wrong password!";
        else {
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
        }


        m.addAttribute("someAttribute", result);
        return "index";
    }

}
