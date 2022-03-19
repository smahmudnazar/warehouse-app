package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private Date date;

    @ManyToOne
    private Warehouse warehouse;


    private String facture_number;

    private String code;

    @Column(nullable = false)
    private boolean active=true;

    @ManyToOne
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Output_product> output_products;

    @ManyToOne
    private Client client;
}
