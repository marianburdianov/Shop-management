package com.ms3.demo.service.service_decl;

import com.ms3.demo.model.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    void updateCustomerById(Customer customer, Long customerId);

    void deleteCustomerById(Long customerId);
}
