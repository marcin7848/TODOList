package TODOList.controllers;

import TODOList.models.Account;
import TODOList.models.Task;
import TODOList.services.AccountService;
import TODOList.services.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        if(!task.getName().matches("^(?!.*(\\\\|')).*$"))
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Task name cannot contain \\\\ and ' \"}"; //cannot contain \ and '

        if(!task.getComment().matches("^(?!.*(\\\\|')).*$"))
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Task comment cannot contain \\\\ and ' \"}"; //cannot contain \ and '

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

    @PostMapping("/edit")
    @ResponseBody
    public String editTask(HttpServletResponse response,
                           @CookieValue(value = "username", required = false) String userCookie,
                           @CookieValue(value = "password", required = false) String passCookie,
                           @ModelAttribute("Task") Task task,
                           @ModelAttribute("newTaskListId") int newTaskListId,
                           @ModelAttribute("newTaskName") String newTaskName,
                           @ModelAttribute("newTaskComment") String newTaskComment,
                           @ModelAttribute("newTaskPriority") int newTaskPriority,
                           @ModelAttribute("newTaskDate") String newTaskDate,
                           @ModelAttribute("newTaskRepeatTime") int newTaskRepeatTime,
                           @ModelAttribute("newTaskDone") boolean newTaskDone){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //please log in

        if(!newTaskName.matches("^(?!.*(\\\\|')).*$"))
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Task name cannot contain \\\\ and ' \"}"; //cannot contain \ and '

        if(!newTaskComment.matches("^(?!.*(\\\\|')).*$"))
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Task comment cannot contain \\\\ and ' \"}"; //cannot contain \ and '

        newTaskDate = newTaskDate.replace("T", " ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(newTaskDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = taskService.editTask(account, task, newTaskListId, newTaskName, newTaskComment, newTaskPriority, new java.sql.Timestamp(parsedDate.getTime()), newTaskRepeatTime, newTaskDone);

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //task doesn't exist

        if(result == 3)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //list doesn't exist

        return "{\"error\":0}"; //task edited
    }

    @PostMapping("/delete/{taskId}")
    @ResponseBody
    public String deleteTask(HttpServletResponse response,
                             @CookieValue(value = "username", required = false) String userCookie,
                             @CookieValue(value = "password", required = false) String passCookie,
                             @PathVariable("taskId") int taskId){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";  //please log in

        int result = taskService.deleteTask(account, taskId);

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";  //tast doesn't exist

        return "{\"error\":0}"; //task deleted
    }

    @PostMapping("/do/{taskId}")
    @ResponseBody
    public String doTask(HttpServletResponse response,
                             @CookieValue(value = "username", required = false) String userCookie,
                             @CookieValue(value = "password", required = false) String passCookie,
                             @PathVariable("taskId") int taskId){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";  //please log in

        int result = taskService.doTask(account, taskId);

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";  //tast doesn't exist

        return "{\"error\":0}"; //task have done
    }

    @GetMapping("/getTask/{taskId}")
    @ResponseBody
    public String getList(HttpServletResponse response,
                          Model m,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie,
                          @PathVariable("taskId") int taskId) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";

        Task task = taskService.getTask(account, taskId);

        if(task == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";

        ObjectMapper mapper = new ObjectMapper();
        String JSONList = null;
        try {
            JSONList = mapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{\"error\":0, \"value\":"+JSONList+"}"; //return list

    }

}
