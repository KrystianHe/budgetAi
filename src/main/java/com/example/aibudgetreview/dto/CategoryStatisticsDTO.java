package com.example.aibudgetreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CategoryStatisticsDTO {
    private Long categoryId;
    private String categoryName;
    private BigDecimal totalAmount;
}
