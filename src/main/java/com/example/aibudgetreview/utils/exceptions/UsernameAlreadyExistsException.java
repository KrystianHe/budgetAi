package com.example.aibudgetreview.utils.exceptions;

import lombok.Data;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Username " + username + " is already taken.");
    }
}
