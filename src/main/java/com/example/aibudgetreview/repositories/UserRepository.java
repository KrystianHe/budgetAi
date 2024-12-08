package com.example.aibudgetreview.repositories;

import com.example.aibudgetreview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

    User getUserById(Long id);

    Optional<User> findByUsername(String username);


}
