package com.example.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeTo {
    @JsonProperty("employeeData")
    private Employee employee;

    @JsonProperty("phoneNumbers")
    private List<String> phoneNumbersList;


}
