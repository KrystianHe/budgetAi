package com.example.aibudgetreview.services;

import com.example.aibudgetreview.models.User;
import com.example.aibudgetreview.models.VerificationCode;
import com.example.aibudgetreview.repositories.VerificationCodeRepository;
import com.example.aibudgetreview.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class VerificationService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MailSender mailSender;

    public void generateAndSendVerificationCode(Long userId) {
        User user = userService.getUserById(userId);

        String code = generateVerificationCode();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setUser(user);
        verificationCode.setCode(code);
        verificationCode.setCreatedAt(LocalDateTime.now());
        verificationCode.setExpirationTime(LocalDateTime.now().plus(10, ChronoUnit.MINUTES));
        verificationCode.setVerified(false);

        verificationCodeRepository.save(verificationCode);

        sendVerificationEmail(user.getEmail(), code);
    }

    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    private void sendVerificationEmail(String email, String code) {
        String subject = "Weryfikacja konta";
        String body = "Twój kod weryfikacyjny: " + code;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public boolean verifyCode(Long userId, String code) {
        VerificationCode verificationCode = verificationCodeRepository
                .findByUserIdAndCodeAndIsVerifiedFalse(userId, code)
                .orElseThrow(() -> new RuntimeException("Invalid or expired code"));

        if (LocalDateTime.now().isAfter(verificationCode.getExpirationTime())) {
            throw new RuntimeException("Verification code expired");
        }

        verificationCode.setVerified(true);
        verificationCodeRepository.save(verificationCode);

        return true;
    }
}
