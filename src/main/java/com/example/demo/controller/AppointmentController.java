package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

@RestController
public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorRepository doctorRepository;
    @PostMapping("Appointment/add")
    public Appointment addAppointment(@RequestBody AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        appointment.setInjury(appointmentDto.getInjury());
        appointment.setPatient(this.patientService.findPatientByCin(appointmentDto.getPatientCin()));
        appointment.setDate(appointmentDto.getDate());
        appointment.setTime(appointmentDto.getTime());
        appointment.setType(appointmentDto.getType());
        appointment.setCreatingDate(java.time.LocalDate.now());
        appointment.setDoctor(this.doctorRepository.findDoctorByEmail(appointmentDto.getDoctorEmail()));
        return this.appointmentRepository.save(appointment);
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("Appointment/All")
    public List<Appointment> getAppointments(){
        return this.appointmentRepository.findAll();
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PutMapping("Appointment/Edit/{id}")
    public Appointment editAppointment(@PathVariable Long id,@RequestBody AppointmentDto appointmentDto){
        appointmentDto.setId(id);
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setInjury(appointmentDto.getInjury());
        appointment.setPatient(this.patientService.findPatientByCin(appointmentDto.getPatientCin()));
        appointment.setDate(appointmentDto.getDate());
        appointment.setTime(appointmentDto.getTime());
        appointment.setType(appointmentDto.getType());
        appointment.setDoctor(this.doctorRepository.findDoctorByEmail(appointmentDto.getDoctorEmail()));
        return this.appointmentRepository.saveAndFlush(appointment);
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @DeleteMapping("Appointment/Delete/{id}")
    public void deleteAppointment(@PathVariable Long id){
        this.appointmentRepository.deleteById(id);
    }

}
