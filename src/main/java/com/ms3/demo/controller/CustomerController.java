package com.ms3.demo.controller;

import com.ms3.demo.model.entities.Customer;
import com.ms3.demo.service.service_decl.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {

        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/")
    public Customer addCustomer(@RequestBody Customer customer) {

        customerService.createCustomer(customer);

        return customer;
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {

        customerService.updateCustomerById(customer, customerId);
        return customer;
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomerById(@PathVariable Long customerId) {

        customerService.deleteCustomerById(customerId);
    }
}
