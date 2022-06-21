package com.example.demo.controller;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

    @PostMapping("doctor/add")
    public Doctor addDoctor(@RequestBody Doctor doctor){
        doctor.setCreatingDate(java.time.LocalDate.now());
        return this.doctorService.addDoctor(doctor);
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("doctor/all")
    public List<Doctor> getAllDoctors(){
        return this.doctorService.getAllDoctors();
    }
    @PutMapping("doctor/update/{id}")
    public void  updateDoctor(@PathVariable Long id,@RequestBody Doctor doctor) {
        System.out.println(doctor);
        this.doctorService.updateDoctor(id,doctor);
    }
    @PutMapping("doctor/update/email/{email}")
    public void editByEmail(@PathVariable String email,@RequestBody Doctor doctor){
        this.doctorService.editByEmail(email,doctor);
    }

    @DeleteMapping("doctor/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.doctorService.deleteById(id);
    }

}
