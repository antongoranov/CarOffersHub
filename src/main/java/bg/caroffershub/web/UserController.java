package bg.caroffershub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    //Login
    @GetMapping("/login")
    public String login(){
        return "auth-login";
    }


}
