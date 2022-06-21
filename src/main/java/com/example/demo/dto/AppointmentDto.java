package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDto{
    private Long id;
    private String patientCin;
    private String doctor;
    private String doctorEmail;
    private String date;
    private String time;
    private String injury;
    private String type;
}
