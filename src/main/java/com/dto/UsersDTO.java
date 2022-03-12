package com.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDTO {

    private String fullName;

    private boolean active;

    private String phone_number;

    private Integer code;

    private List<Integer> warehouseid;


}
