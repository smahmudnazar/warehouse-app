package com.service;

import com.dto.ApiResponse;
import com.dto.ProductDTO;
import com.entity.Category;
import com.entity.Measurement;
import com.entity.Product;
import com.repository.CategoryRepository;
import com.repository.MeasurementRepository;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;


    public ApiResponse save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(randomCode());

        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategory_id());
        product.setCategory(categoryOptional.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurement_id());
        product.setMeasurement(optionalMeasurement.get());


        productRepository.save(product);
        return new ApiResponse("Save", true);
    }


    public ApiResponse edit(Integer id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategory_id());
        Category category = optionalCategory.get();

        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setActive(productDTO.isActive());

        productRepository.save(product);
        return new ApiResponse("Updated", true);
    }

    public Integer randomCode(){

        Random rand = new Random();
        Integer maxNumber = 9999999;

        Integer randomNumber = rand.nextInt(maxNumber) + 1;

        return randomNumber;
    }
}
