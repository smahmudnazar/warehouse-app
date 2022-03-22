package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProductDTO {

    private Integer productId;

    private double amount;

    private double price;

    private boolean active=true;
}
