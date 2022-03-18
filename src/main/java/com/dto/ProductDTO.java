package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;

    private Integer category_id;

    private Integer code;

    private Integer measurement_id;

    private AttachmentDTO attachmentDTO;

    private boolean active;

}
