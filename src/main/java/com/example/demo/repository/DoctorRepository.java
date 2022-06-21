package com.example.demo.repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE doctor d SET d.name = :name," +
            " d.email = :email , d.date = :date ," +
            " d.specialization = :specialization , d.mobile = :mobile ," +
            " d.department = :department , d.degree = :degree " +
            "WHERE d.id = :id ",nativeQuery = true)
    Integer updateDoctor(@Param("id") Long id ,
                      @Param("specialization") String specialization,
                      @Param("email") String email,
                      @Param("date") String date,
                      @Param("mobile") String mobile,
                      @Param("name") String name,
                      @Param("department") String department,
                      @Param("degree") String degree);


    @Modifying
    @Transactional
    @Query(value = "UPDATE doctor d SET d.name = :name," +
            " d.date = :date ," +
            " d.specialization = :specialization , d.mobile = :mobile ," +
            " d.department = :department , d.degree = :degree " +
            "WHERE d.email = :email ",nativeQuery = true)
    Integer editByEmail(@Param("specialization") String specialization,
                        @Param("email") String email,
                        @Param("date") String date,
                        @Param("mobile") String mobile,
                        @Param("name") String name,
                        @Param("department") String department,
                        @Param("degree") String degree);

    @Query(value = "SELECT * FROM doctor d WHERE d.email = :email",nativeQuery = true)
    Doctor findDoctorByEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) FROM Doctor d GROUP BY d.creatingDate")
    List<Integer> findNewDoctorsCountByWeek();
}
