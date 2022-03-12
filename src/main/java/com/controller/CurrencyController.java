package com.controller;

import com.dto.ApiResponse;
import com.entity.Currency;
import com.repository.CurrencyRepository;
import com.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",currencyRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        return "currency/currency";
    }

    @GetMapping("/add")
    public String getAddPage(){
        return "currency/currency-add";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute Currency currency){
        currencyRepository.save(currency);
        return "redirect:/currency";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        currencyRepository.deleteById(id);
        return "redirect:/currency";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (!currencyOptional.isPresent()) return "Xatolik!";
        model.addAttribute("currency", currencyOptional.get());

        return "currency/currency-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Currency currency){
        ApiResponse response=currencyService.edit(id,currency);

        return "redirect:/currency";
    }

}
