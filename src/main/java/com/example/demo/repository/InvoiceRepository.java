package com.example.demo.repository;

import com.example.demo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE invoice i SET i.admission_id = :admissionId," +
            " i.payment_status = :paymentStatus , i.admit_date = :admitDate ," +
            " i.discount = :discount ,i.payment_method = :paymentMethod " +
            "WHERE i.id = :id ",nativeQuery = true)
    Integer updateInvoice(@Param("id") Long id ,
                         @Param("admissionId") String admissionId,
                         @Param("paymentStatus") String paymentStatus,
                         @Param("admitDate") String admitDate,
                         @Param("discount") Long discount,
                          @Param("paymentMethod") String paymentMethod);
}
