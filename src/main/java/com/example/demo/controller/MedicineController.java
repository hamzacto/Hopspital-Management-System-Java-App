package com.example.demo.controller;


import com.example.demo.model.Medicine;
import com.example.demo.repository.MedicineRepository;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class MedicineController {
    @Autowired
    MedicineRepository medicineRepository;

    @PostMapping("Medicine/Add")
    public Medicine addMedicine(@RequestBody Medicine medicine){
        return this.medicineRepository.save(medicine);
    }

    @GetMapping("Medicine/All")
    public List<Medicine> getMedicineList(){
        return this.medicineRepository.findAll();
    }

    @PutMapping("Medicine/Edit/{id}")
    public Medicine updateMedicine(@PathVariable Long id,@RequestBody Medicine medicine){
        return this.medicineRepository.saveAndFlush(medicine);
    }

    @DeleteMapping("Medicine/Delete/{id}")
    public void deleteMedicine(@PathVariable Long id){
        this.medicineRepository.deleteById(id);
    }
}
