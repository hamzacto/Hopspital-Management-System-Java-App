package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Staff implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String gender;
    private String mobile;
    private String designation;
    private String department;
    private String email;
    private String dateOfBirth;
    private String education;

}
