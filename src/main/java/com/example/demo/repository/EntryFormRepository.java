package com.example.demo.repository;

import com.example.demo.model.EntryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryFormRepository extends JpaRepository<EntryForm,Long> {

    @Query(value = "SELECT * FROM entry_form e WHERE e.type = 'ENTRY'",nativeQuery = true)
    List<EntryForm> getEntryFormList();

    @Query(value = "SELECT * FROM entry_form e WHERE e.type = 'EXIT'",nativeQuery = true)
    List<EntryForm> getExitFormList();

}
