package TODOList.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model m) {

        String password = "admin";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        m.addAttribute("someAttribute", hashedPassword);
        return "index";
    }

    @GetMapping("/test")
    public String indexa(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "index";
    }
}
