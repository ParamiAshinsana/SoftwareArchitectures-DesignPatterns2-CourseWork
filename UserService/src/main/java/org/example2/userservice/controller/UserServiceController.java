package org.example2.userservice.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserServiceController {

    @GetMapping("/userService")
    public String getDetail(){
        return "Hello";
    }
}
