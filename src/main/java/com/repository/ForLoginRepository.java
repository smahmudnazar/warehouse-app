package com.repository;

import com.entity.ForLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForLoginRepository extends JpaRepository<ForLogin,Integer> {

     List<ForLogin> findByUsername(String username);

}
