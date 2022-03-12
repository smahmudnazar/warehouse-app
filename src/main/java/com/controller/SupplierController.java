package com.controller;

import com.dto.ApiResponse;
import com.entity.Category;
import com.entity.Currency;
import com.entity.Supplier;
import com.repository.AdminRepository;
import com.repository.SupplierRepository;
import com.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SupplierService supplierService;


    @GetMapping
    public String get(Model model){
        model.addAttribute("list",supplierRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        return "supplier/supplier";
    }
    @GetMapping("/add")
    public String getAddPage(){
        return "supplier/supplier-add";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute Supplier supplier){
        supplierRepository.save(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        supplierRepository.deleteById(id);
        return "redirect:/supplier";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (!supplierOptional.isPresent()) return "Xatolik!";
        model.addAttribute("supplier", supplierOptional.get());

        return "supplier/supplier-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Supplier supplier){
        ApiResponse response=supplierService.edit(id,supplier);
        return "redirect:/supplier";
    }
    //ketmon
    //ketmon
}
