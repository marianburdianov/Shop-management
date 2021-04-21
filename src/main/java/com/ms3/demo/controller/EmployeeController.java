package com.ms3.demo.controller;

import com.ms3.demo.model.entities.Employee;
import com.ms3.demo.service.service_decl.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) {

        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {

        employeeService.createEmployee(employee);

        return employee;
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long employeeId) {

        employeeService.updateEmployeeById(employee, employeeId);
        return employee;
    }

    @DeleteMapping("/{employeeId}")
    public void deleteCustomerById(@PathVariable Long employeeId) {

        employeeService.deleteEmployeeById(employeeId);
    }
}
