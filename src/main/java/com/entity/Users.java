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

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(unique = true)
    private String phone_number;


    private Integer code;

    private String password;

    private boolean active=true;

    @ManyToMany
    private List<Warehouse> warehouse;

}
