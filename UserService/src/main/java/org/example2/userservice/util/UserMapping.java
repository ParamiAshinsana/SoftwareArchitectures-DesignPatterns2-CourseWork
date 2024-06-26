package org.example2.userservice.util;

import lombok.RequiredArgsConstructor;
import org.example2.userservice.dto.UserDTO;
import org.example2.userservice.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapping {
     private final ModelMapper modelMapper;

     public UserDTO toUserDTO(UserEntity userEntity) {
          return  modelMapper.map(userEntity, UserDTO.class);
     }
     public UserEntity toUser(UserDTO userDTO) {
          return  modelMapper.map(userDTO, UserEntity.class);
     }
     public List<UserDTO> toUserDTOList(List<UserEntity> userEntities) {
          return modelMapper.map(userEntities, List.class);
     }
}
