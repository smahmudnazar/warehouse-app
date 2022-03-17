package com.controller;

import com.dto.ApiResponse;
import com.dto.UsersDTO;
import com.entity.Users;
import com.entity.Warehouse;
import com.repository.UserRepository;
import com.repository.WarehouseRepository;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/user")
@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",userRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        return "user/user";
    }


    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("warehouses",warehouseRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "user/user-add";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute UsersDTO dto){
        usersService.add(dto);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Users> byId = userRepository.findById(id);
        if (!byId.isPresent()) return "Xatolik!";
        model.addAttribute("user", byId.get());
        model.addAttribute("list",warehouseRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));

        return "user/user-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,@ModelAttribute UsersDTO dto){
        ApiResponse  response=usersService.edit(id,dto);

        return "redirect:/user";
    }



}
