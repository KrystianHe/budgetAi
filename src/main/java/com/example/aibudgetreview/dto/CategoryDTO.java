package com.example.aibudgetreview.dto;

import com.example.aibudgetreview.models.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private CategoryType type;

}
