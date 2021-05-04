package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.EmployeeDao;
import com.ms3.demo.model.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private static final long ID = 1L;
    private Employee firstEmployee;
    private Employee secondEmployee;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeDao);

        firstEmployee = new Employee();
        firstEmployee.setEmployeeId(1L);
        firstEmployee.setFirstName("Vira");
        firstEmployee.setLastName("Adams");
        firstEmployee.setGender("male");
        firstEmployee.setBirthDate(LocalDate.of(1980, 12, 06));
        firstEmployee.setHireDate(LocalDate.of(2015, 10, 10));
        firstEmployee.setRole("Supervisor");

        secondEmployee = new Employee();
        secondEmployee.setEmployeeId(2L);
        secondEmployee.setFirstName("Steve");
        secondEmployee.setLastName("Baker");
        secondEmployee.setGender("male");
        secondEmployee.setBirthDate(LocalDate.of(1985, 02, 25));
        secondEmployee.setHireDate(LocalDate.of(2011, 12, 22));
        secondEmployee.setRole("Paint Specialist");
    }

    @Test
    void getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(firstEmployee);
        employeeList.add(secondEmployee);

        when(employeeDao.findAll()).thenReturn(employeeList);

        List<Employee> list = employeeService.getAllEmployees();

        assertEquals(2, list.size());

        verify(employeeDao, times(1)).findAll();
    }

    @Test
    void createEmployee() {

        when(employeeDao.save(firstEmployee)).thenReturn(firstEmployee);

        employeeService.createEmployee(firstEmployee);

        verify(employeeDao, times(1)).save(firstEmployee);
    }

    @Test
    void getEmployeeById() {

        when(employeeDao.findByEmployeeId(ID)).thenReturn(firstEmployee);

        Employee employeeForVerify = employeeService.getEmployeeById(ID);

        assertEquals(firstEmployee.getEmployeeId(), employeeForVerify.getEmployeeId());

        verify(employeeDao, times(1)).findByEmployeeId(ID);
    }

    @Test
    void updateEmployeeById() {

        when(employeeDao.findById(ID)).thenReturn(Optional.of(firstEmployee));
        when(employeeDao.save(firstEmployee)).thenReturn(firstEmployee);

        Employee updatedEmployee = employeeService.updateEmployeeById(secondEmployee, ID);

        assertNotNull(updatedEmployee);
        assertEquals(firstEmployee.getEmployeeId(), updatedEmployee.getEmployeeId());
        assertEquals(firstEmployee.getFirstName(), updatedEmployee.getFirstName());
        assertEquals(firstEmployee.getLastName(), updatedEmployee.getLastName());
        assertEquals(firstEmployee.getGender(), updatedEmployee.getGender());

        verify(employeeDao, times(1)).save(updatedEmployee);
    }

    @Test
    void deleteEmployeeById() {

        doNothing().when(employeeDao).deleteById(anyLong());

        employeeService.deleteEmployeeById(ID);

        verify(employeeDao, times(1)).deleteById(ID);
    }
}