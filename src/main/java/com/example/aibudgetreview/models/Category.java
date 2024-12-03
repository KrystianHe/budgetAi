package com.example.aibudgetreview.models;

import com.example.aibudgetreview.models.enums.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;  // 'income' or 'expense'

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Category(Object o, String other, CategoryType categoryType, Object o1) {
    }
}
