package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.EmployeeDao;
import com.ms3.demo.model.entities.Employee;
import com.ms3.demo.service.service_decl.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeDao.findByEmployeeId(employeeId);
    }

    @Override
    public void updateEmployeeById(Employee employeeForUpdate, long employeeId) {

        Employee employee = employeeDao.findByEmployeeId(employeeId);

        employee.setFirstName(employeeForUpdate.getFirstName());
        employee.setLastName(employeeForUpdate.getLastName());
        employee.setGender(employeeForUpdate.getGender());
        employee.setBirthDate(employeeForUpdate.getBirthDate());
        employee.setHireDate(employeeForUpdate.getHireDate());
        employee.setRole(employeeForUpdate.getRole());
        employee.setSalary(employeeForUpdate.getSalary());

        employeeDao.save(employee);
    }

    @Override
    public void deleteEmployeeById(long employeeId) {
        employeeDao.delete(getEmployeeById(employeeId));
    }
}
