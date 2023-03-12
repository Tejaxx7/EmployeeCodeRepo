package com.example.employeeapp.service;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.model.EmployeeTo;
import com.example.employeeapp.model.PhoneNumber;
import com.example.employeeapp.model.TaxResponse;
import com.example.employeeapp.repository.EmployeeRepo;
import com.example.employeeapp.repository.PhoneNumberRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);
    private final EmployeeRepo employeeRepo;
    private final PhoneNumberRepo phoneNumberRepo;
    public EmployeeService(EmployeeRepo employeeRepo,PhoneNumberRepo phoneNumberRepo){
        this.phoneNumberRepo = phoneNumberRepo;
        this.employeeRepo = employeeRepo;
    }


    public List<PhoneNumber> saveEmployee(EmployeeTo employeeTo){
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        employeeTo.getPhoneNumbersList().forEach(p -> phoneNumberList.add(new PhoneNumber(p)));
        phoneNumberList.forEach(phoneNumber -> phoneNumber.setEmployee(employeeTo.getEmployee()));
        employeeRepo.save(employeeTo.getEmployee());
        return phoneNumberRepo.saveAll(phoneNumberList);
    }
    public Employee findEmployee(Long empid){
        return employeeRepo.findById(empid).get();
    }

    public TaxResponse calculateTaxDetails(Long empid){
        Employee employee = findEmployee(empid);
        LOGGER.info("fetched employee-----------");
        LOGGER.info(employee);
        Double salaryPerMonth  = employee.getSalary();
        LocalDate dateOfJoining = employee.getDateOfJoining();
        LOGGER.info("salary per Month: "+ salaryPerMonth);
        LOGGER.info("date of joining: " + dateOfJoining);

        double yearlySalary;
        Double tax;
        double cess = 0.0;

        yearlySalary = calculateYearlySalary(salaryPerMonth,dateOfJoining);
        tax = calculateTax(yearlySalary);

        if(yearlySalary > 2500000){
            cess = (yearlySalary - 2500000) * 0.02;
        }

        return TaxResponse.builder().employeeId(employee.getEmployeeID()).firstName(employee.getFirstName()).lastName(employee.getLastName()).taxAmount(tax).cessAmount(cess).yearlySalary(yearlySalary).build();
    }
    private Double calculateTax(Double yearlySalary){
        double tax;
        if(yearlySalary <= 250000)
            tax = 0.0;
        else if(yearlySalary <= 500000)
            tax = 0.05 * (yearlySalary-250000);
        else if(yearlySalary <= 1000000)
            tax = (0.1*(yearlySalary-500000)) + (0.05*250000);
        else
            tax=(0.2*(yearlySalary-1000000)) + (0.1*500000) + (0.05*250000);
        return tax;
    }
    private Double calculateYearlySalary(Double salaryPerMonth,LocalDate dateOfJoining){
        double lossOfPay;
        int effectivejoinedMonthValue = dateOfJoining.getMonthValue();

        if(effectivejoinedMonthValue < 4 )
            effectivejoinedMonthValue = 12 + effectivejoinedMonthValue;

        int effectiveMonths = 16 - effectivejoinedMonthValue;
        LOGGER.info("effective Months: " + effectiveMonths);

        int lossOfPayDays =  dateOfJoining.getDayOfMonth() -1;
        lossOfPay = lossOfPayDays * (salaryPerMonth/30);
        LOGGER.info("Loss of pay Days: " + lossOfPayDays);
        LOGGER.info("Loss of pay : " + lossOfPay);
        return (salaryPerMonth * effectiveMonths) - (lossOfPay);
    }

}
