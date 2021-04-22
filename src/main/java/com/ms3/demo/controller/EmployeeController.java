package com.ms3.demo.controller;

import com.ms3.demo.model.entities.Employee;
import com.ms3.demo.service.service_decl.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(value = "employee", description = "CRUD Operation for Employee", tags = "EMPLOYEE")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    @ApiOperation(value = "GET ALL EMPLOYEES", notes = "\n" + "This operation gets all employees")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    @ApiOperation(value = "GET EMPLOYEE BY ID", notes = "\n" + "This operation get employee by id")
    public Employee getEmployeeById(@PathVariable Long employeeId) {

        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/employees")
    @ApiOperation(value = "CREATE EMPLOYEE", notes = "\n" + "This operation creates an employee")
    public Employee addEmployee(@RequestBody Employee employee) {

        employeeService.createEmployee(employee);

        return employee;
    }

    @PutMapping("/employees/{employeeId}")
    @ApiOperation(value = "UPDATE EMPLOYEE", notes = "\n" + "This operation updates a existing employee")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long employeeId) {

        employeeService.updateEmployeeById(employee, employeeId);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    @ApiOperation(value = "DELETE EMPLOYEE BY ID", notes = "\n" + "This operation delete employee by id")
    public void deleteCustomerById(@PathVariable Long employeeId) {

        employeeService.deleteEmployeeById(employeeId);
    }
}
