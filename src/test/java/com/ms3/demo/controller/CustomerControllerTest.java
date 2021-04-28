package com.ms3.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms3.demo.model.entities.Customer;
import com.ms3.demo.service.service_decl.CustomerService;
import com.ms3.demo.service.service_impl.CustomerServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ContextConfiguration(classes = CustomerController.class)
class CustomerControllerTest {

    private static final long ID = 1;

    @Autowired
    private CustomerController customerController;
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;
    private Customer customer;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        customer = new Customer();
        customer.setCustomerId(ID);
        customer.setFirstName("Ferdinand");
        customer.setLastName("Magellan");
        customer.setPhoneNumber(123456777L);
        customer.setAddress("Amadora");
    }

    @Test
    void getAllCustomers() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerService.getAllCustomers()).thenReturn(customerList);
        mockMvc.perform(get("/customers")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getCustomerById() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);
        this.mockMvc.perform(get("/customers/{customerId}", ID).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("customerId", equalTo(1)));
    }

    @Test
    void addCustomer() throws Exception {
        Customer testCustomer = new Customer();
        testCustomer.setFirstName("Ally");
        testCustomer.setLastName("Conda");
        testCustomer.setAddress("Kanpur");
        testCustomer.setPhoneNumber(123456777L);

        when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(testCustomer);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(testCustomer));
        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Ally")))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(testCustomer)));
        verify(customerService).createCustomer(testCustomer);
    }

    @Test
    void updateCustomer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Customer updateCustomer = new Customer();
        updateCustomer.setFirstName("Lolly");

        when(customerService.updateCustomerById(Mockito.any(Customer.class), anyLong())).thenReturn(updateCustomer);
        this.mockMvc.perform(put("/customers/{customerId}", customer.getCustomerId().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(updateCustomer)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(jsonPath("firstName", Matchers.is(updateCustomer.getFirstName())));
    }

    @Test
    void deleteCustomerById() throws Exception {
        CustomerService mockedCustomerService = mock(CustomerService.class);

        doNothing().when(mockedCustomerService).deleteCustomerById(ID);
        this.mockMvc.perform(delete("/customers/{customerId}", customer.getCustomerId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        verify(customerService, times(1)).deleteCustomerById(ID);
    }
}
