package com.example.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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

    @NotNull
    @NotEmpty
    @OneToMany(mappedBy = "employee")
    private List<PhoneNumber> phoneNumbers;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dateOfJoining;

    private @NotNull Double salary;
}
