package com.example.employeeapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    @NotNull
    private Long employeeID;
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;
    @Email
    @NotEmpty
    private String email;

    @Transient
    @NotNull
    @NotEmpty
    private PhoneNumbers phoneNumbers;
    @NotBlank
    @NotNull
    private LocalDate dateOfJoining;
    private @NotNull Double salary;
}
