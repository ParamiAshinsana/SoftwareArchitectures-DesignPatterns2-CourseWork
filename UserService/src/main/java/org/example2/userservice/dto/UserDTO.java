package org.example2.userservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String userDOB;
    private String userGender;
    private String userAddress;
    private String userContact;
}
