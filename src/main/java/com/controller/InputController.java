package com.controller;

import com.dto.ApiResponse;
import com.dto.InputDTO;
import com.dto.InputProductDTO;
import com.entity.Input;
import com.entity.Input_product;
import com.entity.Product;
import com.repository.*;
import com.service.InputService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputService inputService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    @GetMapping
    public String getAll(Model model){

        model.addAttribute("list",inputRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "input/input";
    }


    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("productList", productRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("supplierList", supplierRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("warehouseList", warehouseRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("currencyList", currencyRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("today", LocalDate.now().toString());
        return "input/input-add";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute InputDTO dto){
       inputService.add(dto);
       return "redirect:/input";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        Input input = inputOptional.get();
        input.setActive(false);
        inputRepository.save(input);
        return "redirect:/input";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable Integer id){
        Optional<Input> byId = inputRepository.findById(id);
        if (!byId.isPresent()) return "Error";

        model.addAttribute("input",byId.get());
        model.addAttribute("supplierList", supplierRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("warehouseList", warehouseRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("currencyList", currencyRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));

        return "input/input-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute InputDTO inputDTO){
        inputService.edit(id, inputDTO);
        return "redirect:/input";
    }


    @GetMapping("/view/{id}")
    public String inputProductAll(Model model,@PathVariable Integer id) {
        inputService.deleteByExpireDate();
        model.addAttribute("list",inputService.view(id));

        return "/input/input-product";
    }

    @GetMapping("/delete_p/{id}")
    public String delete_p(@PathVariable Integer id) {
        return "redirect:/input/view/"+inputService.delete_p(id);
    }


    @GetMapping("/edit_p/{id}")
    public String getEditProduct(Model model, @PathVariable Integer id) {
        Optional<Input_product> byId = inputProductRepository.findById(id);
        if (!byId.isPresent()) return "Error";

        model.addAttribute("input", byId.get());
        model.addAttribute("productlist", productRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "input/input-product-edit";
    }

    @PostMapping("/edit_p/{id}")
    public String edit_p(@PathVariable Integer id, @ModelAttribute InputProductDTO inputProductDTO){
        inputService.edit_p(id, inputProductDTO);
        Integer integer=0;
        for (Input input : inputRepository.findAll()) {
            for (Input_product inputProduct : input.getInputProductList()) {
                if (inputProduct.getId()==id) {
                    integer=input.getId();
                }
            }
        }
        return "redirect:/input/view/"+integer;
    }


}
