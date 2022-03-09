package com.service;

import com.dto.ApiResponse;
import com.entity.ForLogin;
import com.repository.ForLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    ForLoginRepository userRepository;

    public ApiResponse add(ForLogin users) {
        ForLogin save = userRepository.save(users);
        return new ApiResponse("Saved", true, save);
    }
}
