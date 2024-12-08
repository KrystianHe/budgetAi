package com.example.aibudgetreview.repositories;

import com.example.aibudgetreview.models.Category;
import com.example.aibudgetreview.models.Transaction;
import com.example.aibudgetreview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> getTransactionsById(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId")
    List<Transaction> getTransactionByUserId(@Param("userId") Long userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.category = :category AND t.user = :user")
    BigDecimal findTotalAmountByCategoryAndUser(@Param("user") Category category, @Param("user") User user);

}