package com.ms3.demo.service.service_decl;

import com.ms3.demo.model.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee getEmployeeById(long employeeId);

    Employee updateEmployeeById(Employee employee, long employeeId);

    void deleteEmployeeById(long employeeId);

}