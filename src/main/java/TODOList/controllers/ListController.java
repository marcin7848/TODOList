package TODOList.controllers;

import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.services.AccountService;
import TODOList.services.ListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {

    @Autowired
    ListService listService;

    @PostMapping("/add")
    @ResponseBody
    public String addList(HttpServletResponse response,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie,
                          @ModelAttribute("listName") String listName,
                          @ModelAttribute("listColour") String listColour,
                          @ModelAttribute("numOrder") int numOrder,
                          @ModelAttribute("showed") boolean showed){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //please log in

        if(listName.length() > 14)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"List name max 14 letters!\"}"; //max 14 letters

        if(!listName.matches("^(?!.*(\\\\|')).*$"))
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"List name cannot contain \\\\ and ' \"}"; //cannot contain \ and '

        int result = listService.addList(account, listName, listColour, numOrder, showed);

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"This list already exist!\"}"; //list already existed

        if(result == 3)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad order! Change position!\"}"; //bad numOrder

        return "{\"error\":0}"; //list created
    }

    @PostMapping("/edit")
    @ResponseBody
    public String editList(HttpServletResponse response,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie,
                          @ModelAttribute("Lists") Lists lists,
                           @ModelAttribute("newListName") String newListName,
                           @ModelAttribute("newListColour") String newListColour){
        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //please log in

        if(newListName.length() > 14)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"List name max 14 letters!\"}"; //max 14 letters

        if(!newListName.matches("^(?!.*(\\\\|')).*$"))
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"List name cannot contain \\\\ and ' \"}"; //cannot contain \ and '

        int result = listService.editList(account, lists, newListName, newListColour);

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //list doesn't exist

        if(result == 3)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"This name already exists!\"}"; //list name exists

        return "{\"error\":0}"; //list edited
    }

    @PostMapping("/delete/{listId}")
    @ResponseBody
    public String deleteList(HttpServletResponse response,
                           @CookieValue(value = "username", required = false) String userCookie,
                           @CookieValue(value = "password", required = false) String passCookie,
                           @PathVariable("listId") int listId){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //please log in

        int result = listService.deleteList(account, listId);

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //list doesn't exist

        return "{\"error\":0}"; //list deleted
    }

    @PostMapping("/changeShowed/{listId}")
    public String changeShowedList(HttpServletResponse response,
                             @CookieValue(value = "username", required = false) String userCookie,
                             @CookieValue(value = "password", required = false) String passCookie,
                             @PathVariable("listId") int listId){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in
        int result = listService.changeShowedList(account, listId);
        if(result == 2)
            return "redirect:/"; //list doesn't exist

        return "redirect:/"; //list changed showed
    }

    @PostMapping("/changeNumOrder/{listId}/{numOrder}")
    @ResponseBody
    public String changeNumOrderList(HttpServletResponse response,
                                   @CookieValue(value = "username", required = false) String userCookie,
                                   @CookieValue(value = "password", required = false) String passCookie,
                                   @PathVariable("listId") int listId,
                                     @PathVariable("numOrder") int numOrder){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //please log in

        int result = listService.changeNumOrderList(account, listId, numOrder);

        if(result == 2)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}"; //list doesn't exist

        return "{\"error\":0}"; //changed numOrder
    }

    @GetMapping("/getList/{listId}")
    @ResponseBody
    public String getList(HttpServletResponse response,
                               Model m,
                               @CookieValue(value = "username", required = false) String userCookie,
                               @CookieValue(value = "password", required = false) String passCookie,
                               @PathVariable("listId") int listId) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";

        Lists lists = listService.getList(account, listId);

        if(lists == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";

        ObjectMapper mapper = new ObjectMapper();
        String JSONList = null;
        try {
            JSONList = mapper.writeValueAsString(lists);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{\"error\":0, \"value\":"+JSONList+"}"; //return list

    }

    @GetMapping("/getLists")
    @ResponseBody
    public String getList(HttpServletResponse response,
                          Model m,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";

        List <Lists> lists = listService.getLists(account);

        if(lists.isEmpty())
            return "{\"error\":1, \"errorTitle\":\"Error!\"," +
                    " \"errorDescription\":\"Bad request! Reload and try again!\"}";

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
