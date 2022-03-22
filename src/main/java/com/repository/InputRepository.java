package com.repository;

import com.entity.Input;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface InputRepository extends JpaRepository<Input,Integer> {
    List<Input> findAllByActiveTrue(Sort sort);

    List<Input> findAllByDate(Date date);
}
