package com.controller;

import com.dto.ApiResponse;
import com.entity.Warehouse;
import com.repository.WarehouseRepository;
import com.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("/warehouse")
@Controller
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    WarehouseService warehouseService;


    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",warehouseRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        return "warehouse/warehouse";
    }


    @GetMapping("/add")
    public String getAddPage(){
        return "warehouse/warehouse-add";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute Warehouse warehouse){
        warehouseRepository.save(warehouse);
        return "redirect:/warehouse";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        warehouseRepository.deleteById(id);
        return "redirect:/warehouse";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) return "Xatolik!";
        model.addAttribute("warehouse", optionalWarehouse.get());

        return "warehouse/warehouse-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,@ModelAttribute Warehouse warehouse){
        ApiResponse response=warehouseService.edit(id,warehouse);

        return "redirect:/warehouse";
    }

}
