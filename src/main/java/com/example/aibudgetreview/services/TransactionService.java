package com.example.aibudgetreview.services;

import com.example.aibudgetreview.dto.CategoryDTO;
import com.example.aibudgetreview.models.Category;
import com.example.aibudgetreview.models.Transaction;
import com.example.aibudgetreview.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AITransactionCategorizationService aiTransactionCategorizationService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Dodaj transakcję z automatyczną kategoryzacją.
     *
     * @param transaction transakcja do dodania
     * @return transakcja z przypisaną kategorią DTO
     */
    public Transaction addTransaction(Transaction transaction) {
        CategoryDTO categorizedCategory = aiTransactionCategorizationService.categorizeTransaction(transaction);
        transaction.setCategory(new Category(null, categorizedCategory.getName(), categorizedCategory.getType(), null));

        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.getTransactionsById(id);
    }
}
