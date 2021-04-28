package com.ms3.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms3.demo.model.entities.Product;
import com.ms3.demo.service.service_decl.ProductService;
import com.ms3.demo.service.service_impl.ProductServiceImpl;
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
@ContextConfiguration(classes = ProductController.class)
class ProductControllerTest {

    private static final long ID = 1;

    @Autowired
    private ProductController productController;
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;
    private Product product;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        product = new Product();
        product.setProductId(ID);
        product.setName("Rice");
        product.setPrice(3D);
        product.setQuantity(5);
    }

    @Test
    void getAllProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getAllProducts()).thenReturn(productList);
        mockMvc.perform(get("/products")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getProductById() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(product);
        this.mockMvc.perform(get("/products/{productId}", ID).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productId", equalTo(1)));
    }

    @Test
    void addProduct() throws Exception {
        Product productForAdd = new Product();
        productForAdd.setName("Bread");
        productForAdd.setPrice(5D);
        productForAdd.setQuantity(6);

        when(productService.createProduct(Mockito.any(Product.class))).thenReturn(productForAdd);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(productForAdd));
        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Bread")))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(productForAdd)));
        verify(productService).createProduct(productForAdd);
    }

    @Test
    void updateProduct() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Product productForUpdate = new Product();
        productForUpdate.setName("Cheese");

        when(productService.updateProductById(Mockito.any(Product.class), anyLong())).thenReturn(productForUpdate);
        this.mockMvc.perform(put("/products/{productId}", product.getProductId().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(productForUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(jsonPath("name", Matchers.is(productForUpdate.getName())));
    }

    @Test
    void deleteProductById() throws Exception {
        ProductService mockedCustomerService = mock(ProductService.class);

        doNothing().when(mockedCustomerService).deleteProductById(ID);
        this.mockMvc.perform(delete("/products/{productId}", product.getProductId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        verify(productService, times(1)).deleteProductById(ID);
    }
}