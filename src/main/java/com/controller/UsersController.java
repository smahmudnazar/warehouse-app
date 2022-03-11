package com.controller;

import com.repository.UserRepository;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",userRepository.findAll());
        return "user/user";
    }
}
