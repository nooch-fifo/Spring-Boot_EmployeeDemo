package com.example.EmployeeDemo.ControllerTests;

import com.example.EmployeeDemo.controller.EmployeeController;
import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    // Controller Layer Tests (using MockMvc):
    // Verifies if Correct Service Layer Method is Called & Correct URL Route is Used


    @Test
    public void getAllEmployeesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON));
        verify(employeeService, times(1)).getEmployees();
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
                .contentType(MediaType.APPLICATION_JSON));
        verify(employeeService, times(1)).getEmployeeById(1l);
    }

    @Test
    public void addNewEmployeeTest() throws Exception{
        Employee employee1 = new Employee("John", "Smith", "john_smith123@test.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)));
        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        Employee updatedEmployee = new Employee("Jane", "Doe", "TBA");
        System.out.println(updatedEmployee.getEmail());
        updatedEmployee.setEmail("newEmail@test.com");
        mockMvc.perform((MockMvcRequestBuilders.put("/updateEmployee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedEmployee))));
        verify(employeeService, times(1)).updateEmployee(any(Employee.class));

        // added console logs to confirm if Email is being updated
        System.out.println(updatedEmployee.getEmail());
    }

    @Test
    public void deleteEmployeeTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1")
            .contentType(MediaType.APPLICATION_JSON));
        verify(employeeService, times(1)).deleteEmployee(1L);
    }

}
