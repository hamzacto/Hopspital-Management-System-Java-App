package com.example.demo.repository;

import com.example.demo.model.BilledProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilledProductRepository extends JpaRepository<BilledProduct,Long> {
    @Query(value = "SELECT * FROM billed_product b WHERE b.invoice_id= :invoiceId",nativeQuery = true)
    List<BilledProduct> getProductsByInvoiceId(@Param("invoiceId") Long invoiceId);
}
