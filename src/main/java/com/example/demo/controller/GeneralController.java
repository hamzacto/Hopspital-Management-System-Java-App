package com.example.demo.controller;

import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class GeneralController {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @GetMapping("Patient/Count")
    public Long getPatientsNumber(){
        return this.patientRepository.count();
    }
    @GetMapping("Recipes/Count")
    public Long getRecipesNumber(){
        return this.recipeRepository.count();
    }
    @GetMapping("Appointments/Count")
    public Long getAppointmentsNumber(){
        return this.appointmentRepository.count();
    }
    @GetMapping("Patients/By/Week")
    public List<Integer> getPatientsCountByWeek(){
        return this.patientRepository.findNewPatientsCountByWeek().stream().limit(7).collect(Collectors.toList());
    }
    @GetMapping("Appointments/By/Week")
    public List<Integer> getDoctorsCountByWeek(){
        return this.appointmentRepository.findNewAppointmentsCountByWeek().stream().limit(7).collect(Collectors.toList());
    }
}
