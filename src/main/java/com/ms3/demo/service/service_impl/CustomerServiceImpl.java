package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.CustomerDao;
import com.ms3.demo.model.entities.Customer;
import com.ms3.demo.service.service_decl.CustomerService;
import lombok.RequiredArgsConstructor;
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
    public void updateCustomerById(Customer customerForUpdate, Long customerId) {

        Customer customer = customerDao.findByCustomerId(customerId);

        customer.setAddress(customerForUpdate.getAddress());
        customer.setFirstName(customerForUpdate.getFirstName());
        customer.setLastName(customerForUpdate.getLastName());
        customer.setPhoneNumber(customerForUpdate.getPhoneNumber());

        customerDao.save(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {

        customerDao.delete(getCustomerById(customerId));
    }
}
