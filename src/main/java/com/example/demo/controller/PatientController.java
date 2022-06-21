package com.example.demo.controller;


import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

    @PostMapping("patient/new")
    public Patient createPatient(@RequestBody Patient patient) {
        patient.setCreatingDate(java.time.LocalDate.now());
        System.out.println("added 1");
        return patientRepository.save(patient);

    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

    @GetMapping("patient/All")
    public List<Patient> getAllPatients() {
        System.out.println("added 1");
        return patientRepository.findAll();
    }

    @PutMapping("patient/update/{id}")
    public Patient updatePatient(@PathVariable Long id) {
        return this.patientRepository.saveAndFlush(this.patientRepository.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PutMapping("patient/edit/{cin}")
    public Integer editPatient(@PathVariable String cin, @RequestBody Patient patient) {
        return this.patientRepository.editByCin(cin,
                patient.getGender(),
                patient.getBgroup(),
                patient.getDate(),
                patient.getMobile(),
                patient.getName(),
                patient.getAddress());
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @DeleteMapping("patient/delete/{id}")
    public void deletePatient(@PathVariable("id") Long id){
        this.patientRepository.deleteById(id);
    }
}
