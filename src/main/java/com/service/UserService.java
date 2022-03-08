package com.service;

import com.dto.ApiResponse;
import com.model.Users;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public ApiResponse add(Users users) {
        Users save = userRepository.save(users);
        return new ApiResponse("Saved", true, save);
    }
}
