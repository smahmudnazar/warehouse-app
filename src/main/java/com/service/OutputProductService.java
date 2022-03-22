package com.service;

import com.repository.OutputProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutputProductService {
    final OutputProductRepository outputProductRepository;
}
