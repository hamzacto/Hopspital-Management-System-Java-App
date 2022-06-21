package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Appointment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    private String date;
    private String time;
    private String injury;
    private String type;
    private String status;
    private LocalDate creatingDate;

}
