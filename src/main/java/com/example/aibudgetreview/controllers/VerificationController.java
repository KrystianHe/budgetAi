package com.example.aibudgetreview.controllers;

import com.example.aibudgetreview.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/send/{userId}")
    public String sendVerificationCode(@PathVariable Long userId) {
        verificationService.generateAndSendVerificationCode(userId);
        return "Verification code sent!";
    }

    @PostMapping("/verify")
    public String verifyCode(@RequestParam Long userId, @RequestParam String code) {
        verificationService.verifyCode(userId, code);
        return "Account successfully verified!";
    }
}
