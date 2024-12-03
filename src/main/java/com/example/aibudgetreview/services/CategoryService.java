package com.example.aibudgetreview.services;

import com.example.aibudgetreview.dto.CategoryDTO;
import com.example.aibudgetreview.models.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    /**
     * Metoda konwertująca obiekt Category na CategoryDTO.
     *
     * @param category obiekt Category
     * @return CategoryDTO
     */
    public CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getType());
    }

    /**
     * Metoda konwertująca obiekt CategoryDTO na obiekt Category.
     *
     * @param categoryDTO obiekt CategoryDTO
     * @return Category
     */
    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());
        return category;
    }
}
