package com.example.employeeapp.repository;

import com.example.employeeapp.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber,Long> {
}
