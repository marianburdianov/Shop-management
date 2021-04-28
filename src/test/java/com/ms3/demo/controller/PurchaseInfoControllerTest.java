package com.ms3.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ms3.demo.model.entities.Customer;
import com.ms3.demo.model.entities.Employee;
import com.ms3.demo.model.entities.Product;
import com.ms3.demo.model.entities.PurchaseInfo;
import com.ms3.demo.service.service_impl.PurchaseInfoImpl;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration(classes = PurchaseInfoController.class)
class PurchaseInfoControllerTest {

    private static final long ID = 1;

    @Autowired
    private PurchaseInfoController purchaseInfoController;
    private MockMvc mockMvc;

    @MockBean
    private PurchaseInfoImpl purchaseInfoImpl;
    private PurchaseInfo purchaseInfo;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(purchaseInfoController).build();
        purchaseInfo = new PurchaseInfo();
        purchaseInfo.setPurchaseInfoId(ID);
        purchaseInfo.setCost(300D);
        purchaseInfo.setAmount(7);
        purchaseInfo.setDate(LocalDate.of(2020, 12, 10));
        purchaseInfo.setEmployee(new Employee());
        purchaseInfo.setCustomer(new Customer());
        purchaseInfo.setProductSet(List.of(new Product(), new Product()));
    }

    @Test
    void getAllPurchaseInfo() throws Exception {
        List<PurchaseInfo> purchaseInfosList = new ArrayList<>();

        purchaseInfosList.add(purchaseInfo);
        when(purchaseInfoImpl.getAllPurchaseInfo()).thenReturn(purchaseInfosList);
        mockMvc.perform(get("/purchaseInfo")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getPurchaseInfoById() throws Exception {
        when(purchaseInfoImpl.getPurchaseInfoById(anyLong())).thenReturn(purchaseInfo);
        this.mockMvc.perform(get("/purchaseInfo/{purchaseInfoId}", ID).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("purchaseInfoId", equalTo(1)));
    }

    @Test
    void addPurchaseInfo() throws Exception {//does not work
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        mapper.registerModule(new JavaTimeModule());

        PurchaseInfo purchaseInfoForAdd = new PurchaseInfo();
        purchaseInfoForAdd.setPurchaseInfoId(ID);
        purchaseInfoForAdd.setCost(200D);
        purchaseInfoForAdd.setAmount(5);
        purchaseInfoForAdd.setDate(LocalDate.of(2000, 04, 21));
        purchaseInfoForAdd.setEmployee(new Employee());
        purchaseInfoForAdd.setCustomer(new Customer());
        purchaseInfoForAdd.setProductSet(List.of(new Product(), new Product()));

        when(purchaseInfoImpl.createPurchaseInfo(Mockito.any(PurchaseInfo.class))).thenReturn(purchaseInfoForAdd);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/purchaseInfo")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(purchaseInfoForAdd));
        mockMvc.perform(builder)
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$.amount", is(5)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(purchaseInfoForAdd)));

        verify(purchaseInfoImpl).createPurchaseInfo(purchaseInfoForAdd);
    }

    @Test
    void updatePurchaseInfo() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PurchaseInfo purchaseInfoForUpdate = new PurchaseInfo();
        purchaseInfoForUpdate.setAmount(5);

        when(purchaseInfoImpl.updatePurchaseInfoById(Mockito.any(PurchaseInfo.class), anyLong())).thenReturn(purchaseInfoForUpdate);
        this.mockMvc.perform(put("/purchaseInfo/{purchaseInfoId}", purchaseInfo.getPurchaseInfoId().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(purchaseInfoForUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(jsonPath("amount", Matchers.is(purchaseInfoForUpdate.getAmount())));
    }

    @Test
    void deletePurchaseInfoById() throws Exception {
        PurchaseInfoImpl mockedPurchaseInfoImpl = mock(PurchaseInfoImpl.class);

        doNothing().when(mockedPurchaseInfoImpl).deletePurchaseInfoById(ID);
        this.mockMvc.perform(delete("/purchaseInfo/{purchaseIndoId}", purchaseInfo.getPurchaseInfoId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        verify(purchaseInfoImpl, times(1)).deletePurchaseInfoById(ID);
    }
}