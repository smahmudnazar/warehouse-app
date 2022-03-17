package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDTO {

    private Integer warehouse_id;
    private Integer supplier_id;
    private Integer currency_id;
    private String factura_N;
    private Date date;
    private List<InputProductDTO> inputProducts;
}