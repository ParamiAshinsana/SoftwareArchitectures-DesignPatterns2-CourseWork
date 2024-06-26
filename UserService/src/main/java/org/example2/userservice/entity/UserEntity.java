package org.example2.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String userId;
    private String userName;
    private String userDOB;
    private String userGender;
    private String userAddress;
    private String userContact;
}
