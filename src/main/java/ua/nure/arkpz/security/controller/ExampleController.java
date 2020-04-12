package ua.nure.arkpz.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.security.model.User;

@RestController
public class ExampleController {

    @GetMapping("/example")
    public String examplePage(@RequestBody User user) {
        if (user.getToken() == null) {
            return "You shouldn't see this page! How did you do that?!";
        }
        if (user.getToken() != null) {
            return "You can see this page because you're authorized, " + user.getUsername();
        }
        return "Example page";
    }
}
