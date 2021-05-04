package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.CustomerDao;
import com.ms3.demo.model.entities.Customer;
import com.ms3.demo.service.service_decl.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerDao.findByCustomerId(customerId);
    }

    @Override
    public Customer updateCustomerById(Customer customerForUpdate, Long customerId) {

        final Customer updatedCustomer = customerDao.findById(customerId).orElseThrow(() -> new ApplicationContextException("Customer not found"));

        updatedCustomer.setFirstName(customerForUpdate.getFirstName());
        updatedCustomer.setLastName(customerForUpdate.getLastName());
        updatedCustomer.setPhoneNumber(customerForUpdate.getPhoneNumber());
        updatedCustomer.setAddress(customerForUpdate.getAddress());

        customerDao.save(updatedCustomer);

        return updatedCustomer;
    }

    @Override
    public void deleteCustomerById(Long customerId) {

        customerDao.deleteById(customerId);
    }
}
