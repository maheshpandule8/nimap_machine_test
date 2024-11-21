package com.app.service;


import com.app.model.Category;
import com.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.AotInitializerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category updateCategoryById(Long id, Category categoryDetails) throws AotInitializerNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow();

        category.setName(categoryDetails.getName());
        // If you want to update products as well, handle them here
        // category.setProducts(categoryDetails.getProducts());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) throws AotInitializerNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow();

        categoryRepository.delete(category);
    }
}
