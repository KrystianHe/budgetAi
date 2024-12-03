package com.example.aibudgetreview.repositories;

import com.example.aibudgetreview.models.MonthlyBudget;
import com.example.aibudgetreview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
}
