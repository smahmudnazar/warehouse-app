package com.service;

import com.dto.ApiResponse;
import com.dto.InputProductDTO;
import com.dto.OutputDTO;
import com.dto.OutputProductDTO;
import com.entity.*;
import com.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutputService {
    final OutputRepository outputRepository;
    final OutputProductRepository outputProductRepository;
    final OutputProductService outputProductService;
    final InputRepository inputRepository;
    final InputProductRepository inputProductRepository;
    final  InputService inputService;
    final ProductRepository productRepository;
    final  WarehouseRepository warehouseRepository;
    final  CurrencyRepository currencyRepository;
    final ClientRepository clientRepository;

    public List<Warehouse> warehouses(){
        List<Warehouse> warehouses=new ArrayList<>();
        for (Input input : inputRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id"))){
            warehouses.add(input.getWarehouse());
        }
        return warehouses;
    }

    public List<Product> products(){
        List<Product> products=new ArrayList<>();
        for (Input_product input_product: inputProductRepository.findAllByActiveTrue()){
            products.add(input_product.getProduct());
        }
        return products;
    }
    public List<Currency> currencies(){
        List<Currency> currencies=new ArrayList<>();
        for (Input input : inputRepository.findAllByActiveTrue(Sort.by(Sort.Direction.ASC, "id"))){
            currencies.add(input.getCurrency());
        }
        return currencies;
    }

    public ApiResponse add(OutputDTO dto) {
        Output output = new Output();
        output.setCode(UUID.randomUUID().toString());
        output.setDate(dto.getDate());
        output.setFacture_number(dto.getFactura_N());

        Optional<Currency> optionalCurrency = currencyRepository.findById(dto.getCurrency_id());
        output.setCurrency(optionalCurrency.get());
        Optional<Client> optionalClient = clientRepository.findById(dto.getClient_id());
        output.setClient(optionalClient.get());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(dto.getWarehouse_id());
        output.setWarehouse(optionalWarehouse.get());

        List<OutputProductDTO> outputProductDTOS = dto.getOutputProducts()  ;

        List<Output_product> output_products = new ArrayList<>();

        for (OutputProductDTO outputProductDTO : outputProductDTOS) {
            Output_product output_product = new Output_product();
            Optional<Product> byId = productRepository.findById(outputProductDTO.getProductId());

            output_product.setProduct(byId.get());
            output_product.setPrice(outputProductDTO.getPrice());
            output_product.setAmount(outputProductDTO.getAmount());

            output_products.add(output_product);

        }

        output.setOutput_products(output_products);

        outputRepository.save(output);
        return new ApiResponse("Added", true,output);
    }

    public ApiResponse edit(Integer id, OutputDTO dto) {
        Optional<Output> byId = outputRepository.findById(id);
        Output output = byId.get();
        output.setDate(dto.getDate());


        Optional<Client> client = clientRepository.findById(dto.getClient_id());
        output.setClient(client.get());

        Optional<Warehouse> warehouse = warehouseRepository.findById(dto.getWarehouse_id());
        output.setWarehouse(warehouse.get());

        Optional<Currency> currency = currencyRepository.findById(dto.getCurrency_id());
        output.setCurrency(currency.get());

        outputRepository.save(output);
        return new ApiResponse("Edited",true);


    }

    public List<Output_product> view(Integer id) {
        Optional<Output> byId = outputRepository.findById(id);
        List<Output_product> output_productList = byId.get().getOutput_products();

        List<Output_product> output_products =new ArrayList<>();

        for (Output_product output_product : output_productList) {
            if (output_product.isActive()){
                output_products.add(output_product);
            }
        }
        return output_products;
    }

    public Integer delete_p(Integer id) {
        Optional<Output_product> byId = outputProductRepository.findById(id);
        Output_product output_product = byId.get();
        output_product.setActive(false);
        Integer integer=0;
        outputProductRepository.save(output_product);

        for (Output output : outputRepository.findAll()) {
            for (Output_product outputProduct : output.getOutput_products()) {
                if (outputProduct.getId()==id) integer=output.getId();
            }
        }
        return integer;
    }

    public ApiResponse edit_p(Integer id, OutputProductDTO dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());


        Optional<Output_product> byId = outputProductRepository.findById(id);
        Output_product output_product = byId.get();
        output_product.setProduct(product.get());
        output_product.setAmount(dto.getAmount());
        output_product.setPrice(dto.getPrice());

        outputProductRepository.save(output_product);
        return new ApiResponse("Updated", true);
    }
}
