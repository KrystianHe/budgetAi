package com.example.aibudgetreview.repositories;

import com.example.aibudgetreview.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> getTransactionsById(Long id);
}