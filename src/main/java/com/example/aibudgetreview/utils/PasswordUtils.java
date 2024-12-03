package com.example.aibudgetreview.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Data
public class PasswordUtils {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Metoda do haszowania hasła
     *
     * @param password - hasło użytkownika do zakodowania
     * @return zakodowane hasło
     */
    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Metoda do sprawdzania, czy hasło wprowadzone przez użytkownika pasuje do zakodowanego hasła
     *
     * @param rawPassword - hasło wprowadzone przez użytkownika
     * @param encodedPassword - zakodowane hasło
     * @return true, jeśli hasła są zgodne
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
