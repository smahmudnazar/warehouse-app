package com.controller;

import com.dto.ApiResponse;
import com.dto.InputDTO;
import com.dto.OutputDTO;
import com.dto.OutputProductDTO;
import com.entity.Input;
import com.entity.Input_product;
import com.entity.Output;
import com.entity.Output_product;
import com.repository.*;
import com.service.InputService;
import com.service.OutputProductService;
import com.service.OutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/output")
@RequiredArgsConstructor
public class OutputController {

    final OutputRepository outputRepository;
    final OutputService outputService;
    final OutputProductRepository outputProductRepository;
    final OutputProductService outputProductService;
    final InputRepository inputRepository;
    final  InputProductRepository inputProductRepository;
    final  InputService inputService;
    final  ProductRepository productRepository;
    final  WarehouseRepository warehouseRepository;
    final  CurrencyRepository currencyRepository;
    final ClientRepository clientRepository;


    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",outputRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "output/output";
    }


    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("productList", outputService.products());
        model.addAttribute("clientList", clientRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("warehouseList", outputService.warehouses());
        model.addAttribute("currencyList",outputService.currencies());
        model.addAttribute("today", LocalDate.now().toString());
        return "output/output-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute OutputDTO dto){
        outputService.add(dto);
        return "redirect:/output";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Output> byId = outputRepository.findById(id);
        Output output = byId.get();
        output.setActive(false);
        outputRepository.save(output);

        return "redirect:/output";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable Integer id){
        Optional<Output> byId = outputRepository.findById(id);
        if (!byId.isPresent()) return "Error";

        model.addAttribute("output",byId.get());
        model.addAttribute("clientList", clientRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("warehouseList", outputService.warehouses());
        model.addAttribute("currencyList",outputService.currencies());
        return "output/output-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute OutputDTO dto){
        outputService.edit(id,dto);
        return "redirect:/output";
    }

    @GetMapping("/view/{id}")
    public String inputProductAll(Model model,@PathVariable Integer id) {
        model.addAttribute("list",outputService.view(id));
        return "/output/output-product";
    }

    @GetMapping("/delete_p/{id}")
    public String delete_p(@PathVariable Integer id) {
        return "redirect:/output/view/"+outputService.delete_p(id);
    }

    @GetMapping("/edit_p/{id}")
    public String getEditProduct(Model model, @PathVariable Integer id) {
        Optional<Output_product> byId = outputProductRepository.findById(id);
        if (!byId.isPresent()) return "Error";

        model.addAttribute("output", byId.get());
        model.addAttribute("productList", outputService.products());
        return "output/output-product-edit";
    }

    @PostMapping("/edit_p/{id}")
    public String edit_p(@PathVariable Integer id, @ModelAttribute OutputProductDTO dto){
        outputService.edit_p(id,dto);

        Integer integer=0;
        for (Output output : outputRepository.findAll()) {
            for (Output_product output_product : output.getOutput_products()) {
                if (output_product.getId()==id){
                    integer=output.getId();
                }
            }
        }
        return "redirect:/output/view/"+integer;
    }


}
