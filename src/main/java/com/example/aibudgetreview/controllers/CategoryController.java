package com.example.aibudgetreview.controllers;

import com.example.aibudgetreview.dto.CategoryDTO;
import com.example.aibudgetreview.dto.CategoryStatisticsDTO;
import com.example.aibudgetreview.models.Category;
import com.example.aibudgetreview.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Pobierz wszystkie kategorie
    @GetMapping("/all")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Pobierz kategorię po ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // Dodaj nową kategorię
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.saveCategory(categoryDTO));
    }

    // Aktualizuj istniejącą kategorię
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO updatedCategory) {
        return ResponseEntity.ok(categoryService.updateCategory(id, updatedCategory));
    }

    // Usuń kategorię po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }

    // Wyszukaj kategorie po nazwie
    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> searchCategoriesByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.searchCategoriesByName(name));
    }
    @GetMapping("/statistics")
    public CategoryStatisticsDTO getCategoryStatistics(@RequestParam Long categoryId, @RequestParam Long userId) {
        return categoryService.getCategoryStatistics(categoryId, userId);
    }


}
