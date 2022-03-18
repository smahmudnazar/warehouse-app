package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private boolean active;

    @Column(unique = true)
    private String phone_number;

    @Column(nullable = false,unique = true)
    private String code;

    @ManyToMany
    private List<Warehouse> warehouse;

}
