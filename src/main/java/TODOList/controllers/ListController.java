package TODOList.controllers;

import TODOList.models.Account;
import TODOList.models.Lists;
import TODOList.services.AccountService;
import TODOList.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/list")
public class ListController {

    @Autowired
    ListService listService;

    @GetMapping("/add")
    public String loginView(HttpServletResponse response,
                            @CookieValue(value = "username", required = false) String userCookie,
                            @CookieValue(value = "password", required = false) String passCookie) {

        if (AccountService.validateCookies(new Account(userCookie, passCookie))) {
            return "addList";
        } else {
            return "redirect:/"; //please log in
        }

    }

    @PostMapping("/add")
    public String addList(HttpServletResponse response,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie,
                          @ModelAttribute("listName") String listName,
                          @ModelAttribute("listColour") String listColour){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        listService.addList(account, listName, listColour);

        return "redirect:/"; //list created
    }

    @GetMapping("/edit/{listId}")
    public String editListView(HttpServletResponse response,
                            Model m,
                            @CookieValue(value = "username", required = false) String userCookie,
                            @CookieValue(value = "password", required = false) String passCookie,
                               @PathVariable("listId") int listId) {

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        Lists lists = listService.getList(account, listId);

        if(lists == null)
            return "redirect:/"; //list doesn't exist

        m.addAttribute("someAttribute", "not logged");
        return "editList";

    }

    @PostMapping("/edit")
    public String editList(HttpServletResponse response,
                          @CookieValue(value = "username", required = false) String userCookie,
                          @CookieValue(value = "password", required = false) String passCookie,
                          @ModelAttribute("Lists") Lists lists,
                           @ModelAttribute("newListName") String newListName,
                           @ModelAttribute("newListColour") String newListColour){

        Account account = AccountService.validateCookiesReturnAcc(new Account(userCookie, passCookie));

        if(account == null)
            return "redirect:/"; //please log in

        int result = listService.editList(account, lists, newListName, newListColour);

        if(result == 2)
            return "redirect:/"; //list doesn't exist


        return "redirect:/"; //list edited
    }


}
