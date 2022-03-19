package com.service;

import com.dto.ApiResponse;
import com.dto.InputDTO;
import com.dto.InputProductDTO;
import com.entity.*;
import com.entity.Currency;
import com.repository.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


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
            input_product.setExpireDate(inputProductDTO.getExpireDate());

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
        input_product.setExpireDate(dto.getExpireDate());

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
                if (inputProduct.getId()==id) integer=input.getId();
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


    @SneakyThrows
    public void deleteByExpireDate(){
        List<Input_product> all = inputProductRepository.findAll();
        LocalDate localDate=LocalDate.now();
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        for (Input_product input_product : all) {
            if (!input_product.getExpireDate().after(d)){
                input_product.setActive(false);
            }
            inputProductRepository.save(input_product);
        }
    }


    public ApiResponse edit(Integer id, InputDTO dto) {
        Optional<Input> byId = inputRepository.findById(id);
        Input input = byId.get();
        input.setDate(dto.getDate());

        Optional<Supplier> supplier = supplierRepository.findById(dto.getSupplier_id());
        input.setSupplier(supplier.get());

        Optional<Warehouse> warehouse = warehouseRepository.findById(dto.getWarehouse_id());
        input.setWarehouse(warehouse.get());

        Optional<Currency> currency = currencyRepository.findById(dto.getCurrency_id());
        input.setCurrency(currency.get());

        inputRepository.save(input);
        return new ApiResponse("Edited",true);
    }
}
