package com.ms3.demo.model.dao;

import com.ms3.demo.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

    Employee findByEmployeeId(Long id);
}
