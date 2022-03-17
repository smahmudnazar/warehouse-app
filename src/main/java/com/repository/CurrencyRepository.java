package com.repository;

import com.entity.Currency;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    List<Currency> findAllByActiveTrue(Sort sort);
}
