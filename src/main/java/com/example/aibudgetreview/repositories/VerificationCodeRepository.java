package com.example.aibudgetreview.repositories;

import com.example.aibudgetreview.models.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    Optional<VerificationCode> findByUserIdAndCodeAndIsVerifiedFalse(Long userId, String code);

    Optional<VerificationCode> findByUserIdAndIsVerifiedFalse(Long userId);
    @Query("DELETE FROM VerificationCode v WHERE v.expirationTime < :now AND v.isVerified = false")
    void deleteExpiredUnverifiedCodes(LocalDateTime now);

}

