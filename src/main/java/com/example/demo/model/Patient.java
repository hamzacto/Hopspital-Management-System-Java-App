package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;
    private String img;

//    private String last;
    private String gender;
    private String bgroup;
    private String date;
    private String mobile;
    private String name;
    private String address;
    private String treatment;
    private String cin;
    private LocalDate creatingDate;
//    private String email;
//    private String maritalStatus;
//
//    private Long bloodPresure;
//    private String sugger;
//    private String injury;

}
