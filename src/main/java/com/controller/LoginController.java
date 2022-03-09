package com.controller;

import com.entity.ForLogin;
import com.repository.ForLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ForLoginRepository userRepository;


    @GetMapping
    public String reg(Model model){
        return "index";
    }

    @PostMapping
    public String iseuser(Model model, @RequestParam("usernm") String usernm, @RequestParam("userpsw")String pass){
        List<ForLogin> javob= userRepository.findByUsername(usernm);
        if (javob.get(0).getPassword().equals(pass)){
            return "home/home";
        }else {
            return "index";
        }

    }

}
