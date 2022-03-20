package com.controller;

import com.dto.DailyDTO;
import com.dto.DateDTO;
import com.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    final DashboardService dashboardService;

    @GetMapping
    public String getPage(){
        return "/dashboard/dashboard";
    }

    @GetMapping("/dailyInput")
    public String inputs(){
        return "/dashboard/daily-input";
    }

    @PostMapping("/dailyInput")
    public String getInputs(Model model, @ModelAttribute DateDTO date){
        List<DailyDTO> dailyDTOS = dashboardService.dailyInputDto(date);
        model.addAttribute("list",dailyDTOS);
        return "/dashboard/daily-inputs";
    }

    @GetMapping("/dailyOutput")
    public String outputs(){
        return "/dashboard/daily-output";
    }

    @PostMapping("/dailyOutput")
    public String getOutputs(Model model, @ModelAttribute DateDTO date){
        List<DailyDTO> dailyDTOS = dashboardService.dailyOutputDto(date);
        model.addAttribute("list",dailyDTOS);
        return "/dashboard/daily-outputs";
    }

    @GetMapping("/expired")
    public String expired(Model model){
        model.addAttribute("list",dashboardService.expired());
        return "/dashboard/expired";
    }

}
