package com.controller;

import com.entity.Users;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserRepository userRepository;


    @GetMapping
    public String reg(Model model){
        return "index";
    }

    @PostMapping
    public String iseuser(Model model, @RequestParam("usernm") String usernm, @RequestParam("userpsw")String pass){
        List<Users> javob= userRepository.findByUsername(usernm);
        if (javob.get(0).getPassword().equals(pass)){
            return "home/home";
        }else {
            return "index";
        }

    }

}
