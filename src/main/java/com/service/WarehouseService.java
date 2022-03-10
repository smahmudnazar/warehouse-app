package com.service;

import com.dto.ApiResponse;
import com.entity.Warehouse;
import com.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        Warehouse warehouse1 = byId.get();

        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse.isActive());

        warehouseRepository.save(warehouse1);
        return new ApiResponse("Saved",true);
    }
}
