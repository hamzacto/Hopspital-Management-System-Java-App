package com.example.demo.repository;

import com.example.demo.model.ItemEntryForm;
import com.example.demo.util.mostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemEntryFormRepository extends JpaRepository<ItemEntryForm,Long> {
    @Query(value = "SELECT * FROM item_entry_form i WHERE i.type = 'EXIT' GROUP BY i.item_id  LIMIT 10" ,nativeQuery = true)
    public List<ItemEntryForm> getMostConsumedItems();
    @Query(value = "SELECT * FROM item_entry_form i WHERE i.type = 'ENTRY' GROUP BY i.item_id  LIMIT 10" ,nativeQuery = true)
    public List<ItemEntryForm> getMostPurchasedItems();
    @Query(value = "SELECT * FROM item_entry_form i" ,nativeQuery = true)
    public List<ItemEntryForm> getLatestActivity();

    @Query(value="SELECT SUM(quantity) FROM item_entry_form i WHERE i.type = 'ENTRY' GROUP BY MONTH(i.date) LIMIT 7",nativeQuery = true)
    public List<Long> getBestPurchased();
    @Query(value="SELECT SUM(quantity) FROM item_entry_form i WHERE i.type = 'EXIT' GROUP BY MONTH(i.date) LIMIT 7",nativeQuery = true)
    public List<Long> getBestConsumed();

}
