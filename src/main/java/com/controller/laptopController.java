package com.controller;

import com.repository.ForLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/laptop")
public class laptopController {
    @Autowired
    ForLoginRepository userRepository;


    @GetMapping
    public String get(Model model){
        return "laptop/laptop";
    }
}
