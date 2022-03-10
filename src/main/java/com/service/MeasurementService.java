package com.service;

import com.dto.ApiResponse;
import com.entity.Measurement;
import com.entity.Warehouse;
import com.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public ApiResponse edit(Integer id, Measurement measurement) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        Measurement measurement1 = byId.get();

        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());

        measurementRepository.save(measurement1);
        return new ApiResponse("Saved",true);
    }
}
