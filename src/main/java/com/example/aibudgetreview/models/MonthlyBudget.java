package com.example.aibudgetreview.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "monthly_budget")
public class MonthlyBudget extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer month;  // 1-12

    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "budget_amount", nullable = false)
    private BigDecimal budgetAmount;
}
