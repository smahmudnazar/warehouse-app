package com.service;

import com.dto.ApiResponse;
import com.entity.Admin;
import com.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminService {
    @Autowired
    AdminRepository userRepository;

    public ApiResponse add(Admin users) {
        Admin save = userRepository.save(users);
        return new ApiResponse("Saved", true, save);
    }
}
