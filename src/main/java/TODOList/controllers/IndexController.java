package TODOList.controllers;

import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.services.AccountService;
import TODOList.services.ListService;
import TODOList.services.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    AccountService accountService;

    @Autowired
    TaskService taskService;

    @Autowired
    ListService listService;

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
        m.addAttribute("lists", listService.getLists(account));


        return "index";
    }


    @GetMapping("/getLists")
    @ResponseBody
    public String getLists(HttpServletResponse response,
                          Model m,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";

        List<Lists> lists = listService.getLists(account);

        if(lists == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"You have no lists!\"}";

        ObjectMapper mapper = new ObjectMapper();
        String JSONList = null;
        try {
            JSONList = mapper.writeValueAsString(lists);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{\"error\":0, \"value\":"+JSONList+"}"; //return list

    }
}
