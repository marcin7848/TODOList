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
import java.util.Date;

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

    @GetMapping("/edit/{taskId}")
    public String editTaskView(HttpServletResponse response,
                               Model m,
                               @CookieValue(value = "username", required = false) String userCookie,
                               @CookieValue(value = "password", required = false) String passCookie,
                               @PathVariable("taskId") int taskId) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        Task task = taskService.getTask(account, taskId);

        if(task == null)
            return "redirect:/"; //list doesn't exist

        m.addAttribute("someAttribute", "not logged");
        return "editTask";

    }

    @PostMapping("/edit")
    public String editTask(HttpServletResponse response,
                           @CookieValue(value = "username", required = false) String userCookie,
                           @CookieValue(value = "password", required = false) String passCookie,
                           @ModelAttribute("Task") Task task,
                           @ModelAttribute("newTaskListId") int newTaskListId,
                           @ModelAttribute("newTaskName") String newTaskName,
                           @ModelAttribute("newTaskComment") String newTaskComment,
                           @ModelAttribute("newTaskPriority") int newTaskPriority,
                           @ModelAttribute("newTaskDate") Date newTaskDate,
                           @ModelAttribute("newTaskRepeatTime") int newTaskRepeatTime,
                           @ModelAttribute("newTaskDone") int newTaskDone){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        int result = taskService.editTask(account, task, newTaskListId, newTaskName, newTaskComment, newTaskPriority, newTaskDate, newTaskRepeatTime, newTaskDone);

        if(result == 2)
            return "redirect:/"; //list doesn't exist


        return "redirect:/"; //list edited
    }

    @PostMapping("/delete/{taskId}")
    public String deleteTask(HttpServletResponse response,
                             @CookieValue(value = "username", required = false) String userCookie,
                             @CookieValue(value = "password", required = false) String passCookie,
                             @PathVariable("taskId") int taskId){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        int result = taskService.deleteTask(account, taskId);

        if(result == 2)
            return "redirect:/"; //tast doesn't exist

        return "redirect:/"; //task deleted
    }

}
