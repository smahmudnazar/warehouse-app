package com.controller;

import com.dto.ApiResponse;
import com.entity.Measurement;
import com.entity.Warehouse;
import com.repository.MeasurementRepository;
import com.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/measurement")
public class MeasurementController {
    
    @Autowired
    MeasurementRepository measurementRepository;
    
    @Autowired
    MeasurementService measurementService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",measurementRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id")));
        return "measurement/measurement";
    }

    @GetMapping("/add")
    public String getAddPage(){
        return "measurement/measurement-add";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute Measurement measurement){
        measurementRepository.save(measurement);
        return "redirect:/measurement";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Measurement> byId = measurementRepository.findById(id);
        Measurement measurement = byId.get();
        measurement.setActive(false);
        measurementRepository.save(measurement);
        return "redirect:/measurement";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) return "Xatolik!";
        model.addAttribute("measurement", optionalMeasurement.get());

        return "measurement/measurement-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,@ModelAttribute Measurement measurement){
        ApiResponse response=measurementService.edit(id,measurement);

        return "redirect:/measurement";
    }

}
