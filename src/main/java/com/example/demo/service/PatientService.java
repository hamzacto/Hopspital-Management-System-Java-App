package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
     @Autowired
    public PatientRepository patientRepository;
     public Patient findPatientByCin(String cin){
         return this.patientRepository.findPatientByCin(cin);
     }
}
