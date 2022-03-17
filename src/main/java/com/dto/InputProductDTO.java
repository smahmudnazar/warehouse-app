package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDTO {
    private Integer productId;
    private double amount;
    private double price;
    private Date expireDate;
}
