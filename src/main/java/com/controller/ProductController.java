package com.controller;

import com.dto.ApiResponse;
import com.dto.ProductDTO;
import com.entity.Product;
import com.repository.*;
import com.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    AttachmentRepositry attachmentRepositry;



    @GetMapping
    public String getProduct(Model model) {
        model.addAttribute("listProduct", productRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "product/product";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("categorytList", categoryRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("measurementList", measurementRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));

        return "product/product-add";
    }

    @PostMapping("/add")
    public String addPage(@ModelAttribute ProductDTO productDTO, @RequestParam("file")MultipartFile file) {
        productService.save(productDTO,file);
        return "redirect:/product";
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        Product product = byId.get();
        product.setActive(false);
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String getEditProduct(Model model, @PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) return "Error";

        model.addAttribute("product", product.get());
        model.addAttribute("categoryList", categoryRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("measurementList", measurementRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "product/product-edit";
    }

    @SneakyThrows
    @PostMapping("/edit/{id}")
    public String editSave(@PathVariable Integer id, @ModelAttribute ProductDTO productDTO, @RequestParam("file")MultipartFile file) {
        ApiResponse response = productService.edit(id, productDTO,file);
        return "redirect:/product";

    }


}
