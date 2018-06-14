package TODOList.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model m, Principal username) {

        if(username == null) {
            m.addAttribute("someAttribute", "not logged");
            return "index";
        }
        else{
            m.addAttribute("someAttribute", "logged");
            return "index";
        }
    }

    @GetMapping("/test")
    public String indexa(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "index";
    }
}
