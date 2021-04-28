package com.ms3.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms3.demo.model.entities.Employee;
import com.ms3.demo.service.service_decl.EmployeeService;
import com.ms3.demo.service.service_impl.EmployeeServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration(classes = EmployeeController.class)
class EmployeeControllerTest {

    private static final long ID = 1;

    @Autowired
    private EmployeeController employeeController;
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;
    private Employee employee;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        employee = new Employee();
        employee.setEmployeeId(ID);
        employee.setFirstName("Tadd");
        employee.setLastName("Robinson");
        employee.setGender("male");
        employee.setBirthDate(LocalDate.of(2000, 01, 07));
        employee.setHireDate(LocalDate.of(2021, 04, 23));
        employee.setRole("Manager");
        employee.setSalary(3800D);
    }

    @Test
    void getAllEmployees() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        mockMvc.perform(get("/employees")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);
        this.mockMvc.perform(get("/employees/{employeeId}", ID).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("employeeId", equalTo(1)));
    }

    @Test
    void addEmployee() throws Exception {
        Employee employeeToAdd = new Employee();
        employeeToAdd.setFirstName("Nick");
        employeeToAdd.setLastName("Dicky");
        employeeToAdd.setRole("Supervisor");

        when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(employeeToAdd);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(employeeToAdd));
        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Nick")))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(employeeToAdd)));
        verify(employeeService).createEmployee(employeeToAdd);
    }

    @Test
    void updateEmployee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Employee employeeForUpdate = new Employee();
        employeeForUpdate.setFirstName("Hanna");

        when(employeeService.updateEmployeeById(Mockito.any(Employee.class), anyLong())).thenReturn(employeeForUpdate);
        this.mockMvc.perform(put("/employees/{employeeId}", employee.getEmployeeId().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(employeeForUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(jsonPath("firstName", Matchers.is(employeeForUpdate.getFirstName())));
    }

    @Test
    void deleteCustomerById() throws Exception {
        EmployeeService mockedEmployeeService = mock(EmployeeService.class);

        doNothing().when(mockedEmployeeService).deleteEmployeeById(ID);
        this.mockMvc.perform(delete("/employees/{employeeId}", employee.getEmployeeId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        verify(employeeService, times(1)).deleteEmployeeById(ID);
    }
}