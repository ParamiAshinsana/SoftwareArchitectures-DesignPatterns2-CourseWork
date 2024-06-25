package org.example2.userservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.userservice.dto.UserDTO;
import org.example2.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO getSelectedUser(String id) {
        return null;
    }
}
