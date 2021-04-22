package com.ms3.demo.controller;

import com.ms3.demo.model.entities.Customer;
import com.ms3.demo.service.service_decl.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
@Api(value = "customer", description = "CRUD Operations for Customer", tags = "CUSTOMER")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ApiOperation(value = "GET ALL CUSTOMERS", notes = "\n" + "This operation gets all customers")
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    @ApiOperation(value = "GET CUSTOMER BY ID", notes = "\n" + "This operation get customer by id")
    public Customer getCustomerById(@PathVariable Long customerId) {

        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    @ApiOperation(value = "CREATE CUSTOMER", notes = "\n" + "This operation creates a customer")
    public Customer addCustomer(@RequestBody Customer customer) {

        customerService.createCustomer(customer);

        return customer;
    }

    @PutMapping("/{customerId}")
    @ApiOperation(value = "UPDATE CUSTOMER", notes = "\n" + "This operation updates a existing customer")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {

        customerService.updateCustomerById(customer, customerId);
        return customer;
    }

    @DeleteMapping("/{customerId}")
    @ApiOperation(value = "DELETE CUSTOMER BY ID", notes = "\n" + "This operation delete customer by id")
    public void deleteCustomerById(@PathVariable Long customerId) {

        customerService.deleteCustomerById(customerId);
    }
}
