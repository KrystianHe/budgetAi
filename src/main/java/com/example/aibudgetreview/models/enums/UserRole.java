package com.example.aibudgetreview.models.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum UserRole {
    ADMIN("Administrator", "Pełny dostęp do wszystkich zasobów"),
    USER("Użytkownik", "Standardowy dostęp do podstawowych funkcji"),
    MODERATOR("Moderator", "Dostęp do zarządzania treściami"),
    GUEST("Gość", "Ograniczony dostęp, tylko do odczytu");

    private final String roleName;
    private final String description;


}
