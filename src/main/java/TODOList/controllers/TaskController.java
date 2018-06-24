package TODOList.controllers;

import TODOList.models.Account;
import TODOList.models.Task;
import TODOList.services.AccountService;
import TODOList.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/add")
    public String addTaskView(HttpServletResponse response,
                            @CookieValue(value = "username", required = false) String userCookie,
                            @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            //getLists for this account
            return "addTask";
        } else {
            return "redirect:/"; //please log in
        }

    }

    @PostMapping("/add")
    public String addTask(HttpServletResponse response,
                          Model m,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie,
                          @ModelAttribute("Task") Task task){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        int result = taskService.addTask(account, task);
        if(result == 2)
            return "redirect:/"; //list doesn't exist

        return "redirect:/"; //task created

    }

}
