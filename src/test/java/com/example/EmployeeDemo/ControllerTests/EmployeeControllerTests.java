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

}
