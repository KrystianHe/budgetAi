package com.example.aibudgetreview.controllers;

import com.example.aibudgetreview.dto.UserDTO;
import com.example.aibudgetreview.models.User;
import com.example.aibudgetreview.services.UserService;
import com.example.aibudgetreview.utils.exceptions.EmailAlreadyRegisteredException;
import com.example.aibudgetreview.utils.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UsernameAlreadyExistsException | EmailAlreadyRegisteredException ex) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }  catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
