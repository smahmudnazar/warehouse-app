package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp date;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    private Integer facture_number;

    @Column(unique = true)
    private Integer code;

    @ManyToOne
    private Client client;
}
