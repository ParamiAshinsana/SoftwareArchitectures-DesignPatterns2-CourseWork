package org.example2.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userId;
    private String userName;
    private String userDOB;
    private String userGender;
    private String userAddress;
    private String userContact;
}
