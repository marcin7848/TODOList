package TODOList.controllers;

import TODOList.models.Account;
import TODOList.services.AccountService;
import TODOList.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class IndexController {


    @Autowired
    AccountService accountService;

    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String index(Model m,
                        HttpServletResponse response,
                            @CookieValue(value = "username", required = false) String userCookie,
                            @CookieValue(value = "password", required = false) String passCookie) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null) {
            AccountService.deleteCookies(response);
            return "indexLogin";
        }

        m.addAttribute("username", account.getUsername());
        m.addAttribute("firstName", account.getFirstName());
        m.addAttribute("secondName", account.getSecondName());
        m.addAttribute("email", account.getEmail());
        m.addAttribute("remindersTasks", taskService.getTasksRemindersToday(account));
        return "index";

    }
}
