package com.example.aibudgetreview.services;

import com.example.aibudgetreview.dto.UserDTO;
import com.example.aibudgetreview.models.User;
import com.example.aibudgetreview.repositories.UserRepository;
import com.example.aibudgetreview.utils.PasswordUtils;
import com.example.aibudgetreview.utils.exceptions.EmailAlreadyRegisteredException;
import com.example.aibudgetreview.utils.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(userDTO.getUsername());
        }
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException(userDTO.getEmail());
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(PasswordUtils.encodePassword(userDTO.getPasswordHash()));

        return userRepository.save(user);
    }
    public User updateUser(Long id, UserDTO updatedUserDTO) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(updatedUserDTO.getStatus());
        user.setEmail(updatedUserDTO.getEmail());
        user.setUsername(updatedUserDTO.getUsername());

        if (updatedUserDTO.getPasswordHash() != null) {
            user.setPasswordHash(PasswordUtils.encodePassword(updatedUserDTO.getPasswordHash()));
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers(Specification<User> specification) {
        return userRepository.findAll(specification);
    }

    public User getUserById(Long id){
        return userRepository.getUserById(id);
    }


    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (PasswordUtils.matches(password, user.getPasswordHash())) {
            return user;
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

}
