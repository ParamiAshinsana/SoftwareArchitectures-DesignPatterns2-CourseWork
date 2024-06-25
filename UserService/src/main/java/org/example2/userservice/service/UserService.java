package org.example2.userservice.service;

import org.example2.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    void deleteUser(String id);
    void updateUser(String id, UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getSelectedUser(String id);
}
