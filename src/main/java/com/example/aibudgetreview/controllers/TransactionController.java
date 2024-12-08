package com.example.aibudgetreview.controllers;

import com.example.aibudgetreview.dto.CategoryExpenseDTO;
import com.example.aibudgetreview.models.Transaction;
import com.example.aibudgetreview.repositories.TransactionRepository;
import com.example.aibudgetreview.services.AITransactionCategorizationService;
import com.example.aibudgetreview.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AITransactionCategorizationService aiTransactionCategorizationService;
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
    @PostMapping("/train")
    public String trainModel() {
        try {
            aiTransactionCategorizationService.trainModel();
            return "Model successfully trained!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to train model: " + e.getMessage();
        }
    }
    @GetMapping("/user/{userId}")
    public List<CategoryExpenseDTO> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getCategoryExpenses(userId);
    }
}
