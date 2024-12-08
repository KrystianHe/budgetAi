package com.example.aibudgetreview.services;

import com.example.aibudgetreview.dto.CategoryDTO;
import com.example.aibudgetreview.dto.CategoryExpenseDTO;
import com.example.aibudgetreview.models.Category;
import com.example.aibudgetreview.models.Transaction;
import com.example.aibudgetreview.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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


    public List<CategoryExpenseDTO> getCategoryExpenses(Long userId) {
        List<Transaction> transactions = transactionRepository.getTransactionByUserId(userId);

        Map<String, BigDecimal> categoryExpenses = new HashMap<>();

        for (Transaction transaction : transactions) {
            String categoryName = transaction.getCategory().getName();
            BigDecimal amount = transaction.getAmount();

            categoryExpenses.put(categoryName, categoryExpenses.getOrDefault(categoryName, BigDecimal.ZERO).add(amount));
        }

        List<CategoryExpenseDTO> expenseData = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : categoryExpenses.entrySet()) {
            CategoryExpenseDTO dto = new CategoryExpenseDTO(entry.getKey(), entry.getValue());
            expenseData.add(dto);
        }
        return expenseData;
    }
}
