package com.repository;

import com.entity.Input_product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Repository
public interface InputProductRepository extends JpaRepository<Input_product,Integer> {
    List<Input_product> findAllByActiveTrue();
    List<Input_product> findAllByExpireDateBefore(Date date);
}
