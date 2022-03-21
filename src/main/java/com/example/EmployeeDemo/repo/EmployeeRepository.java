package com.example.EmployeeDemo.repo;

import com.example.EmployeeDemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
