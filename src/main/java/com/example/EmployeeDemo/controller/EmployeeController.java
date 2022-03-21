package com.example.EmployeeDemo.controller;

import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // CRUD Functionality

    // Create
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    // Read
    @GetMapping("/")
    public List<Employee> getAllEmployees(){
        return this.employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return this.employeeService.getEmployeeById(id);
    }

    // Update
    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
}
