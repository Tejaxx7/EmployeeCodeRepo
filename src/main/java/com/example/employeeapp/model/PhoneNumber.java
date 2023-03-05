package com.example.employeeapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;

    @ManyToOne
    private Employee employee;

    public PhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }


}
