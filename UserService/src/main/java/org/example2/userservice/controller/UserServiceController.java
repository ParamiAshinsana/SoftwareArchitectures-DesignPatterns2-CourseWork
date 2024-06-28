package org.example2.userservice.controller;
import lombok.RequiredArgsConstructor;
import org.example2.userservice.dto.UserDTO;
import org.example2.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(value = "/updateUser/{id}")
    public void updateUser(@RequestBody UserDTO userDTO, @PathVariable ("id") String id){
        userService.updateUser(id,userDTO);
        System.out.println("User Updated!");
    }

    @GetMapping(value = "/getAllUsers")
    List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getSelectedUser/{id}")
    ResponseEntity<UserDTO> getSelectedUser(@PathVariable ("id") String id){
        UserDTO selectedUser = userService.getSelectedUser(id);
        return selectedUser != null ? ResponseEntity.ok(selectedUser) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
