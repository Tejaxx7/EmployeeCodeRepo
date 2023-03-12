package com.example.employeeapp.controller;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.model.EmployeeTo;
import com.example.employeeapp.model.PhoneNumber;
import com.example.employeeapp.model.TaxResponse;
import com.example.employeeapp.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<List<PhoneNumber>> addEmployee(@RequestBody @Valid EmployeeTo employeeTo){
        LOGGER.info(employeeTo);
        List<PhoneNumber> phoneNumbers = employeeService.saveEmployee(employeeTo);
        return new ResponseEntity<>(phoneNumbers,HttpStatus.OK);
    }

    @GetMapping("/getTax/{empid}")
    public ResponseEntity<Object> getEmployeeTax(@PathVariable  Long empid){
        TaxResponse t =  employeeService.calculateTaxDetails(empid);
        return new ResponseEntity<>(t,HttpStatus.OK);

    }
    @GetMapping("/getEmployee/{empid}")
    public ResponseEntity<Object> getEmployee(@PathVariable Long empid){
        return new ResponseEntity<>(employeeService.findEmployee(empid),HttpStatus.OK);

    }

}
