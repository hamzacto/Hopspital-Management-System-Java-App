package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class StaffController {
    @Autowired
    StaffRepository staffRepository;

    @PostMapping("Staff/Add")
    public Staff addStaff(@RequestBody Staff staff){
        return this.staffRepository.save(staff);
    }

    @GetMapping("Staff/All")
    public List<Staff> getStaff(){
        return this.staffRepository.findAll();
    }


    @PutMapping("Staff/Edit/{id}")
    public Staff EditStaff(@PathVariable Long id){
        Staff staff = this.staffRepository.findById(id).get();
        return this.staffRepository.saveAndFlush(staff);
    }

    @DeleteMapping("Staff/Delete/{id}")
    public void deleteStaff(@PathVariable Long id){
        this.staffRepository.deleteById(id);
    }
}
