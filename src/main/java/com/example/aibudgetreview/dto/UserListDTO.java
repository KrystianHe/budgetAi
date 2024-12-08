package com.example.aibudgetreview.dto;

import com.example.aibudgetreview.models.enums.UserRole;
import com.example.aibudgetreview.models.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserListDTO {

    private String username;
    private String email;
    private UserRole userRole;    // Rola użytkownika
    private UserStatus status;

}
