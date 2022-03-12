package com.service;

import com.dto.ApiResponse;
import com.dto.UsersDTO;
import com.entity.Users;
import com.entity.Warehouse;
import com.repository.UserRepository;
import com.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public void add(UsersDTO dto) {
        List<Warehouse> allByActiveTrue = warehouseRepository.findAllByActiveTrue();

        List<Warehouse> allById = warehouseRepository.findAllById(dto.getWarehouseid());

        List<Warehouse> all=new ArrayList<>();

        for (Warehouse warehouse : allByActiveTrue) {
            for (Warehouse warehouse1 : allById) {
                if (warehouse.getId().equals(warehouse1.getId())){
                    all.add(warehouse1);
                }
            }
        }

        Users users=new Users();
        users.setFullName(dto.getFullName());
        users.setActive(dto.isActive());
        users.setCode(randomCode());
        users.setPhone_number(dto.getPhone_number());
        users.setWarehouse(all);

      userRepository.save(users);


    }

    public Integer randomCode(){

        Random rand = new Random();
        Integer maxNumber = 9999;

        Integer randomNumber = rand.nextInt(maxNumber) + 1;

        return randomNumber;
    }

    public ApiResponse edit(Integer id, UsersDTO dto) {
        List<Warehouse> allByActiveTrue = warehouseRepository.findAllByActiveTrue();

        List<Warehouse> allById = warehouseRepository.findAllById(dto.getWarehouseid());

        List<Warehouse> all=new ArrayList<>();

        for (Warehouse warehouse : allByActiveTrue) {
            for (Warehouse warehouse1 : allById) {
                if (warehouse.getId().equals(warehouse1.getId())){
                    all.add(warehouse1);
                }
            }
        }

        Users users=new Users();
        users.setFullName(dto.getFullName());
        users.setActive(dto.isActive());
        users.setCode(randomCode());
        users.setId(id);
        users.setPhone_number(dto.getPhone_number());
        users.setWarehouse(all);

        userRepository.save(users);
        return new ApiResponse("Saved",true);

    }
}
