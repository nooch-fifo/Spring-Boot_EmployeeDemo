package com.example.EmployeeDemo.service;

import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    // dependency injection
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    // Create
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Read
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow();
        return ResponseEntity.ok(employee);
    }

    // Update
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        return employeeRepository.save(employee);
    }

    // Delete
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee with ID: " + id + "has been successfully deleted!";
    }
}
