package com.example.demo.repository;

import com.example.demo.model.BilledProduct;
import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query(value = "SELECT * FROM item i WHERE i.entry_form_id= :id",nativeQuery = true)
    List<Item> getItemsByFormId(@Param("id") Long id);
}
