package com.ms3.demo.model.dao;

import com.ms3.demo.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

    Customer findByCustomerId(Long id);
}