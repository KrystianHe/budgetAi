package com.example.aibudgetreview.services;

import com.example.aibudgetreview.dto.CategoryDTO;
import com.example.aibudgetreview.dto.CategoryStatisticsDTO;
import com.example.aibudgetreview.models.Category;
import com.example.aibudgetreview.models.User;
import com.example.aibudgetreview.repositories.CategoryRepository;
import com.example.aibudgetreview.repositories.TransactionRepository;
import com.example.aibudgetreview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName(), category.getType()))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        return new CategoryDTO(category.getId(), category.getName(), category.getType());
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());
        Category savedCategory = categoryRepository.save(category);

        return new CategoryDTO(savedCategory.getId(), savedCategory.getName(), savedCategory.getType());
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO updatedCategoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        category.setName(updatedCategoryDTO.getName());
        category.setType(updatedCategoryDTO.getType());
        Category updatedCategory = categoryRepository.save(category);

        return new CategoryDTO(updatedCategory.getId(), updatedCategory.getName(), updatedCategory.getType());
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        categoryRepository.delete(category);
    }

    public List<CategoryDTO> searchCategoriesByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name).stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName(), category.getType()))
                .collect(Collectors.toList());
    }

    public CategoryStatisticsDTO getCategoryStatistics(Long categoryId, Long userId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BigDecimal totalAmount = transactionRepository.findTotalAmountByCategoryAndUser(category, user);
        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }

        return new CategoryStatisticsDTO(category.getId(), category.getName(), totalAmount);
    }

}
