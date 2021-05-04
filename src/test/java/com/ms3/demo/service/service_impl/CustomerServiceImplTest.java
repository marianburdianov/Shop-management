package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.CustomerDao;
import com.ms3.demo.model.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private static final long ID = 1L;
    private Customer firstCustomer;
    private Customer secondCustomer;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerDao);

        firstCustomer = new Customer();
        firstCustomer.setCustomerId(1L);
        firstCustomer.setFirstName("James");
        firstCustomer.setLastName("Smith");
        firstCustomer.setPhoneNumber(1234567L);
        firstCustomer.setAddress("Chisinau");

        secondCustomer = new Customer();
        secondCustomer.setCustomerId(2L);
        secondCustomer.setFirstName("Robert");
        secondCustomer.setLastName("Jones");
        secondCustomer.setPhoneNumber(25252525L);
        secondCustomer.setAddress("Tampa");
    }

    @Test
    void getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(firstCustomer);
        customerList.add(secondCustomer);

        when(customerDao.findAll()).thenReturn(customerList);

        List<Customer> list = customerService.getAllCustomers();

        assertEquals(2, list.size());

        verify(customerDao, times(1)).findAll();
    }

    @Test
    void createCustomer() {

        when(customerDao.save(firstCustomer)).thenReturn(firstCustomer);

        customerService.createCustomer(firstCustomer);

        verify(customerDao, times(1)).save(firstCustomer);

    }

    @Test
    void getCustomerById() {

        lenient().when(customerDao.findByCustomerId(ID)).thenReturn(firstCustomer);

        Customer customerForVerify = customerService.getCustomerById(ID);

        assertEquals(firstCustomer.getCustomerId(), customerForVerify.getCustomerId());

        verify(customerDao, times(1)).findByCustomerId(ID);
    }

    @Test
    void updateCustomerById() {

        when(customerDao.findById(ID)).thenReturn(Optional.of(firstCustomer));
        when(customerDao.save(firstCustomer)).thenReturn(firstCustomer);

        Customer updatedCustomer = customerService.updateCustomerById(secondCustomer, ID);

        assertNotNull(updatedCustomer);
        assertEquals(firstCustomer.getCustomerId(), updatedCustomer.getCustomerId());
        assertEquals(firstCustomer.getFirstName(), updatedCustomer.getFirstName());
        assertEquals(firstCustomer.getLastName(), updatedCustomer.getLastName());
        assertEquals(firstCustomer.getPhoneNumber(), updatedCustomer.getPhoneNumber());
        assertEquals(firstCustomer.getAddress(), updatedCustomer.getAddress());

        verify(customerDao, times(1)).save(updatedCustomer);
    }

    @Test
    void deleteCustomerById() {

        doNothing().when(customerDao).deleteById(anyLong());

        customerService.deleteCustomerById(ID);

        verify(customerDao, times(1)).deleteById(ID);
    }
}