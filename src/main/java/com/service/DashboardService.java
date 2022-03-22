package com.service;

import com.dto.ApiResponse;
import com.dto.DailyDTO;
import com.dto.DateDTO;
import com.entity.Input;
import com.entity.Input_product;
import com.entity.Output;
import com.entity.Output_product;
import com.repository.InputProductRepository;
import com.repository.InputRepository;
import com.repository.OutputRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    final InputRepository inputRepository;
    final OutputRepository outputRepository;
    final InputProductRepository inputProductRepository;

    @SneakyThrows
    public List<DailyDTO> dailyInputDto(DateDTO date){
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getDate());
        List<Input> allByDate = inputRepository.findAllByDate(date1);
        List<DailyDTO> dailyDTOS=new ArrayList<>();

        for (Input input : allByDate) {
            for (Input_product input_product : input.getInputProductList()) {
                if (input_product.isActive()) {
                    DailyDTO dailyDTO = new DailyDTO();
                    dailyDTO.setProductName(input_product.getProduct().getName());
                    dailyDTO.setAmount(input_product.getAmount());
                    dailyDTO.setSumma(input_product.getAmount() * input_product.getPrice());

                    dailyDTOS.add(dailyDTO);
                }
            }
        }
        return dailyDTOS;
    }

    @SneakyThrows
    public List<DailyDTO> dailyOutputDto(DateDTO date){
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getDate());
        List<Output> allByDate = outputRepository.findAllByDate(date1);
        List<DailyDTO> dailyDTOS=new ArrayList<>();

        for (Output output : allByDate) {
            for (Output_product outputProduct : output.getOutput_products()) {
                if (outputProduct.isActive()) {
                    DailyDTO dailyDTO = new DailyDTO();
                    dailyDTO.setProductName(outputProduct.getProduct().getName());
                    dailyDTO.setAmount(outputProduct.getAmount());
                    dailyDTO.setSumma(outputProduct.getAmount() * outputProduct.getPrice());

                    dailyDTOS.add(dailyDTO);
                }
            }
        }
        return dailyDTOS;
    }

    @SneakyThrows
    public List<Input_product> expired(){
        Long currentTime = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().plusDays(5).toString()).getTime();

        Date mustDate = new Date(currentTime + (86400 * 1000 * 3));
        List<Input_product> dateBefore = inputProductRepository.findAllByExpireDateBefore(mustDate);

        List<Input_product> input_products=new ArrayList<>();
        for (Input_product input_product : dateBefore) {
            if (input_product.isActive()) input_products.add(input_product);
        }

        return input_products;
    }
}
