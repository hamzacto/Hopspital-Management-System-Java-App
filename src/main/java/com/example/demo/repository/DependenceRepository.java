package com.example.demo.repository;

import com.example.demo.model.Dependence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DependenceRepository extends JpaRepository<Dependence,Long> {

    @Query(value = "SELECT SUM(amount) FROM dependence",nativeQuery = true)
    Long getTotalExpenses();
}
