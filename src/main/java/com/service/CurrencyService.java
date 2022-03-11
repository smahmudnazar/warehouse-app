package com.service;

import com.dto.ApiResponse;
import com.entity.Currency;
import com.entity.Warehouse;
import com.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {
@Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse edit(Integer id, Currency currency) {
        Optional<Currency> byId = currencyRepository.findById(id);
        Currency currency1= byId.get();

        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());

        currencyRepository.save(currency1);
        return new ApiResponse("Saved",true);
    }
}
