package com.controller;

import com.entity.Users;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    static int count=0;


    @Autowired
    UserRepository userRepository;


    @GetMapping
    public String reg(Model model){
        return "register/register";
    }

    @PostMapping
    public Object save(Model model ,@RequestParam("username")String username,@RequestParam("psw")String pass){
        Users users1 = new Users(count,username,pass);
        userRepository.save(users1);
        return "index";
    }


}
