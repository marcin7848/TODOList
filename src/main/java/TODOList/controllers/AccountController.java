package TODOList.controllers;

import TODOList.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import TODOList.models.Account;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController {

    @Autowired
    AccountDao accountDao;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/loginProcess")
    public String loginProcess(Model m, HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("Account") Account login) {

        System.out.println(login.getUsername());
        Account account = accountDao.validateAccount(login);

        String result;
        if(account == null) result = "Wrong password!";
        else result = account.getUsername();


        m.addAttribute("someAttribute", result);
        return "index";
    }

}
