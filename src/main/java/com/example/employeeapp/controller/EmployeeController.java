package com.example.employeeapp.controller;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.model.TaxResponse;
import com.example.employeeapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NegativeOrZero;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Object> addEmployee(@RequestBody @Valid Employee employee){
        Employee e = employeeService.saveEmployee(employee);
       return new ResponseEntity<>(e,HttpStatus.OK);
    }

    @GetMapping("/getTax/{empid}")
    public ResponseEntity<Object> getEmployeeTax(@PathVariable  Long empid){
        TaxResponse t =  employeeService.calculateTaxDetails(empid);
        return new ResponseEntity<>(t,HttpStatus.OK);

    }
}
