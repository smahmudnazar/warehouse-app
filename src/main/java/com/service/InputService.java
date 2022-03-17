package com.service;

import com.dto.ApiResponse;
import com.dto.InputDTO;
import com.dto.InputProductDTO;
import com.dto.ProductDTO;
import com.entity.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    public ApiResponse add(InputDTO inputDTO) {

        Input input=new Input();
        input.setDate(inputDTO.getDate());
        input.setCode(UUID.randomUUID().toString());
        input.setFacture_number(inputDTO.getFactura_N());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrency_id());
        input.setCurrency(optionalCurrency.get());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplier_id());
        input.setSupplier(optionalSupplier.get());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouse_id());
        input.setWarehouse(optionalWarehouse.get());


        List<InputProductDTO> inputProductDTOList = inputDTO.getInputProducts();

        List<Input_product> input_products=new ArrayList<>();

        for (InputProductDTO inputProductDTO : inputProductDTOList) {
            Input_product input_product=new Input_product();
            Optional<Product> byId = productRepository.findById(inputProductDTO.getProductId());

            input_product.setProduct(byId.get());
            input_product.setAmount(inputProductDTO.getAmount());
            input_product.setPrice(inputProductDTO.getPrice());
            input_product.setExpire_date(inputProductDTO.getExpireDate());

            input_products.add(input_product);
        }

        input.setInputProductList(input_products);

        inputRepository.save(input);
        return new ApiResponse("Added", true, input);
    }


    public ApiResponse edit_p(Integer id, InputProductDTO dto) {
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        Product product = optionalProduct.get();

        Optional<Input_product> byId = inputProductRepository.findById(id);
        Input_product input_product = byId.get();
        input_product.setProduct(product);
        input_product.setPrice(dto.getPrice());
        input_product.setAmount(dto.getAmount());
        input_product.setExpire_date(dto.getExpireDate());

        inputProductRepository.save(input_product);
        return new ApiResponse("Updated", true);
    }



    public Integer delete_p(Integer id){
        Optional<Input_product> byId = inputProductRepository.findById(id);
        Input_product input_product = byId.get();
        input_product.setActive(false);
        Integer integer=0;
        inputProductRepository.save(input_product);
        for (Input input : inputRepository.findAll()) {
            for (Input_product inputProduct : input.getInputProductList()) {
                if (inputProduct.getId()==id) {
                    integer=input.getId();
                }
            }
        }
        return integer;
    }

    public List<Input_product> view(Integer id){
        Optional<Input> byId = inputRepository.findById(id);
        List<Input_product> inputProductList =byId.get().getInputProductList();

        List<Input_product> input_products=new ArrayList<>();

        for (Input_product input_product : inputProductList) {
            if (input_product.isActive()){
                input_products.add(input_product);
            }
        }
        return input_products;
    }
}
