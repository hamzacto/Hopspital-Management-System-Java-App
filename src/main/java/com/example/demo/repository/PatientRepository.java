package com.example.demo.repository;

import com.example.demo.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE patient p SET p.gender = :gender, p.bgroup = :bgroup, p.date = :date, p.mobile = :mobile, p.name = :name, p.address = :address " +
            "WHERE p.cin = :cin ",nativeQuery = true)
    Integer editByCin(@Param("cin") String cin ,
                      @Param("gender") String gender,
                      @Param("bgroup") String bgroup,
                      @Param("date") String date,
                      @Param("mobile") String mobile,
                      @Param("name") String name,
                      @Param("address") String address);
    @Query(value = "SELECT * FROM Patient p WHERE p.cin = :cin",nativeQuery = true)
    Patient findPatientByCin(@Param("cin") String cin);

    @Query(value = "SELECT COUNT(*) FROM Patient p GROUP BY p.creatingDate")
    List<Integer> findNewPatientsCountByWeek();

}
