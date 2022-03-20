package com.service;

import com.dto.ApiResponse;
import com.entity.Category;
import com.entity.Warehouse;
import com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse edit(Integer id, Category category) {
        Optional<Category> byId = categoryRepository.findById(id);
        Category category1 = byId.get();

        category1.setName(category.getName());
        category1.setActive(category.isActive());

        categoryRepository.save(category1);
        return new ApiResponse("Saved", true);
    }
}
