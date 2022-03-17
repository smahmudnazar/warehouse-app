package com.repository;

import com.entity.Supplier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository <Supplier,Integer>{

    List<Supplier> findAllByActiveTrue(Sort sort);

}
