package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public void  updateDoctor(Long id , Doctor doctor) {
        this.doctorRepository.updateDoctor(this.doctorRepository.getById(id).getId(),
                doctor.getSpecialization(),
                doctor.getEmail(),
                doctor.getDate(),
                doctor.getMobile(),
                doctor.getName(),
                doctor.getDepartment(),
                doctor.getDegree());
    }

    public Doctor addDoctor(Doctor doctor){
        return this.doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return this.doctorRepository.findAll();
    }

    public Integer editByEmail(String email, Doctor doctor) {
        return this.doctorRepository.editByEmail(
                doctor.getSpecialization(),
                doctor.getEmail(),
                doctor.getDate(),
                doctor.getMobile(),
                doctor.getName(),
                doctor.getDepartment(),
                doctor.getDegree());
    }

    public void deleteById(Long id) {
        this.doctorRepository.deleteById(id);
    }
}
