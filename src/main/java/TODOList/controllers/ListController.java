package TODOList.controllers;

import TODOList.models.Account;
import TODOList.services.AccountService;
import TODOList.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


}
