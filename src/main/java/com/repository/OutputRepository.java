package com.repository;

import com.entity.Input;
import com.entity.Output;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OutputRepository extends JpaRepository<Output,Integer> {
    List<Output> findAllByActiveTrue(Sort sort);
    List<Output> findAllByDate(Date date);
}
