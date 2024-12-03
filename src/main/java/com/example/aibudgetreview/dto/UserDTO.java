package com.example.aibudgetreview.dto;

import com.example.aibudgetreview.models.enums.UserRole;
import com.example.aibudgetreview.models.enums.UserStatus;
import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String email;
    private String passwordHash;  // Hasło użytkownika
    private UserRole userRole;    // Rola użytkownika
    private UserStatus status;

}
