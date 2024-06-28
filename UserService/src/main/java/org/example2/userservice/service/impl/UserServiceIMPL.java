package org.example2.userservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.userservice.dto.UserDTO;
import org.example2.userservice.entity.UserEntity;
import org.example2.userservice.exception.NotFoundException;
import org.example2.userservice.repository.UserDAO;
import org.example2.userservice.service.UserService;
import org.example2.userservice.util.UserMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserDAO userDAO;
    private final UserMapping userMapping;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapping.toUser(userDTO);

        userEntity = userDAO.save(userEntity);
        return userMapping.toUserDTO(userEntity);
    }

    @Override
    public void deleteUser(String id) {
        if(!userDAO.existsById(id)) throw new NotFoundException("User not found");
        userDAO.deleteById(id);
    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDAO.findById(id);
        if(!tmpUser.isPresent()) throw new NotFoundException("User not found");
        tmpUser.get().setUserName(userDTO.getUserName());
        tmpUser.get().setUserDOB(userDTO.getUserDOB());
        tmpUser.get().setUserGender(userDTO.getUserGender());
        tmpUser.get().setUserAddress(userDTO.getUserAddress());
        tmpUser.get().setUserContact(userDTO.getUserContact());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapping.toUserDTOList(userDAO.findAll());
    }

    @Override
    public UserDTO getSelectedUser(String id) {
        if(!userDAO.existsById(id)) throw new NotFoundException("User not found");
        return userMapping.toUserDTO(userDAO.getReferenceById(id));
    }
}
