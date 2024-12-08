package com.example.aibudgetreview.services.task;

import com.example.aibudgetreview.repositories.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationCleanupTask {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Scheduled(fixedRate = 120000)
    public void cleanExpiredVerificationCodes() {
        verificationCodeRepository.deleteExpiredUnverifiedCodes(LocalDateTime.now());
    }
}

