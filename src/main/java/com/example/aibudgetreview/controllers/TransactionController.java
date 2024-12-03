package com.example.aibudgetreview.controllers;

import com.example.aibudgetreview.models.Transaction;
import com.example.aibudgetreview.repositories.TransactionRepository;
import com.example.aibudgetreview.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;
    /**
     * Endpoint do dodawania transakcji
     *
     * @param transaction transakcja do dodania
     * @return transakcja z przypisaną kategorią DTO
     */
    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    /**
     * Endpoint do pobrania transakcji po ID
     *
     * @param id identyfikator transakcji
     * @return transakcja z przypisaną kategorią DTO
     */
    @GetMapping("/{id}")
    public Optional<Transaction> getTransaction(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }
}
