package com.service;

import com.dto.ApiResponse;
import com.entity.Supplier;
import com.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public ApiResponse edit(Integer id, Supplier supplier) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        Supplier supplier1= byId.get();

        supplier1.setName(supplier.getName());
        supplier1.setActive(supplier.isActive());
        supplier1.setPhone_number(supplier.getPhone_number());

        supplierRepository.save(supplier1);
        return new ApiResponse("Saved",true);
    }
}
