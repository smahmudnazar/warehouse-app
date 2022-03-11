package com.entity;

import com.entity.Shablon.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsNameEntity {

    @ManyToOne
    private Category category;

    @ManyToOne
    private Attachment photo;

    @Column(nullable = false,unique = true)
    private Integer code;

    @ManyToOne
    private Measurement measurement;

    private boolean active=true;
}
