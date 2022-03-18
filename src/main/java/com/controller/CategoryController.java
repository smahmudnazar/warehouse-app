package com.controller;

import com.dto.ApiResponse;
import com.entity.Category;
import com.entity.Input;
import com.entity.Warehouse;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",categoryRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "category/category";
    }

    @GetMapping("/add")
    public String getAddPage(){
        return "category/category-add";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.get();
        category.setActive(false);
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) return "Xatolik!";
        model.addAttribute("category",categoryOptional.get());

        return "category/category-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,@ModelAttribute Category category){
        ApiResponse response=categoryService.edit(id,category);

        return "redirect:/category";
    }


}
