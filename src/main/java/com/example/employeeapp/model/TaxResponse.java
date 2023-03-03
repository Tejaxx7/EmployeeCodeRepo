package com.example.employeeapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaxResponse {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Double yearlySalary;
    private Double taxAmount;
    private Double cessAmount;
}
