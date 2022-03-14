package com.controller;

import com.dto.ApiResponse;
import com.dto.ProductDTO;
import com.entity.Product;
import com.repository.CategoryRepository;
import com.repository.MeasurementRepository;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;


    @GetMapping
    public String getProduct(Model model) {
        model.addAttribute("listProduct", productRepository.findAll());
        return "product/product";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("categorytList", categoryRepository.findAllByActiveTrue());
        model.addAttribute("measurementList", measurementRepository.findAllByActiveTrue());
        return "product/product-add";
    }

    @PostMapping("/add")
    public String addPage(@ModelAttribute ProductDTO productDTO) {
        productService.save(productDTO);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String getEditProduct(Model model, @PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) return "Error";

        model.addAttribute("product", product.get());
        model.addAttribute("categoryList", categoryRepository.findAllByActiveTrue());
        model.addAttribute("measurementList", measurementRepository.findAllByActiveTrue());
        return "product/product-edit";
    }

    @PostMapping("/edit/{id}")
    public String editSave(@PathVariable Integer id, @ModelAttribute ProductDTO productDTO) {
        ApiResponse response = productService.edit(id, productDTO);
        return "redirect:/product";

    }


}
