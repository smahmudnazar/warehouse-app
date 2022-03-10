package com.controller;

import com.entity.Admin;
import com.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    static int count=0;


    @Autowired
    AdminRepository adminRepositoryy;


    @GetMapping
    public String reg(Model model){
        return "register/register";
    }

    @PostMapping
    public String save(Model model ,@RequestParam("username")String username,@RequestParam("psw")String pass){
        Admin users1 = new Admin(count,username,pass);
        adminRepositoryy.save(users1);
        return "index";
    }


}
