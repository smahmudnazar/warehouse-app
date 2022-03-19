package com.controller;

import com.dto.ApiResponse;
import com.entity.Client;
import com.repository.ClientRepository;
import com.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;


    @GetMapping
    public String get(Model model){
        model.addAttribute("list",clientRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "client/client";
    }
    @GetMapping("/add")
    public String getAddPage(){
        return "client/client-add";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute Client client){
        clientRepository.save(client);
        return "redirect:/client";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Client> byId = clientRepository.findById(id);
        Client client = byId.get();
        client.setActive(false);
        clientRepository.save(client);
        return "redirect:/client";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) return "Xatolik!";
        model.addAttribute("client", clientOptional.get());

        return "client/client-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Client client){
        clientService.edit(id,client);
        return "redirect:/client";
    }
}
