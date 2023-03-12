package com.example.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneNumber;

    @JsonBackReference
    @ManyToOne
    private Employee employee;

    public PhoneNumber(String phoneNumber){
        System.out.println("phoneNumber constructor invoked: " + phoneNumber);
        this.phoneNumber = phoneNumber;
    }


}
