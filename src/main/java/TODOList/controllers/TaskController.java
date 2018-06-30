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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    @ResponseBody
    public String addTask(HttpServletResponse response,
                          Model m,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie,
                          @ModelAttribute("Task") Task task,
                          @ModelAttribute("dateTime") String dateTime) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //please log in

        dateTime = dateTime.replace("T", " ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dateTime);
            task.setDate(new java.sql.Timestamp(parsedDate.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = taskService.addTask(account, task);
        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //list doesn't exist

        return "{\"error\":0}"; //task created

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
