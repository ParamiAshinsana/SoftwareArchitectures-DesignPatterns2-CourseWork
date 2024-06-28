package org.example2.userservice.controller;
import lombok.RequiredArgsConstructor;
import org.example2.userservice.dto.UserDTO;
import org.example2.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserServiceController {
    private final UserService userService;

    @GetMapping("/userService")
    public String getDetail(){
        return "Hello";
    }

    @PostMapping(value = "/saveUser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable ("id") String id){
        userService.deleteUser(id);
    }
}
