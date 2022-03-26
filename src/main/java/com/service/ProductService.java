package com.service;

import com.dto.ApiResponse;
import com.dto.ProductDTO;
import com.entity.*;
import com.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    final AttachmentRepositry attachmentRepositry;


    @SneakyThrows
    public ApiResponse save(ProductDTO productDTO,@RequestParam("file") MultipartFile file) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(UUID.randomUUID().toString());
        product.setActive(productDTO.isActive());

        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategory_id());
        product.setCategory(categoryOptional.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurement_id());
        product.setMeasurement(optionalMeasurement.get());

        Attachment attachment=new Attachment();
        attachment.setContentType(file.getContentType());
        attachment.setSize(file.getSize());
        attachment.setName(file.getOriginalFilename());
        attachment.setBytes(file.getBytes());
        attachment.setImg(Base64.getEncoder().encodeToString(file.getBytes()));
        attachmentRepositry.save(attachment);

        product.setPhoto(attachment);

        productRepository.save(product);
        return new ApiResponse("Save", true);
    }


    public ApiResponse edit(Integer id, ProductDTO productDTO,@RequestParam("file") MultipartFile file) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategory_id());
        Category category = optionalCategory.get();

        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setActive(productDTO.isActive());

        Optional<Attachment> byId = attachmentRepositry.findById(product.getPhoto().getId());
        Attachment attachment = byId.get();
        if (!file.isEmpty()){
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            attachment.setName(file.getOriginalFilename());
            attachment.setBytes(file.getBytes());
            attachment.setImg(Base64.getEncoder().encodeToString(file.getBytes()));
            attachmentRepositry.save(attachment);
        }
        product.setPhoto(attachment);
        productRepository.save(product);
        return new ApiResponse("Updated", true);
    }

}
