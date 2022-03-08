package com.controller;

import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/watch")
public class WatchController {
    @Autowired
    UserRepository userRepository;


    @GetMapping
    public String get(Model model){
        return "watch/watch";
    }
}
