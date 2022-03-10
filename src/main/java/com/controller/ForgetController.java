package com.controller;

import com.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forgetuser")
public class ForgetController {
    @Autowired
    AdminRepository userRepository;


    @GetMapping
    public String get(Model model){
        return "home/forgetuser";
    }
}
