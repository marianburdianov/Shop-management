package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.ProductDao;
import com.ms3.demo.model.entities.Product;
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
class ProductServiceImplTest {

    private static final long ID = 1L;
    private Product firstProduct;
    private Product secondProduct;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDao productDao;

    @BeforeEach
    void setUP() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productDao);

        firstProduct = new Product();
        firstProduct.setProductId(1L);
        firstProduct.setName("Cheese");
        firstProduct.setPrice(4D);
        firstProduct.setQuantity(5);

        secondProduct = new Product();
        secondProduct.setProductId(2L);
        secondProduct.setName("Potatoes");
        secondProduct.setPrice(0.75);
        secondProduct.setQuantity(7);
    }

    @Test
    void getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(firstProduct);
        productList.add(secondProduct);

        when(productDao.findAll()).thenReturn(productList);

        List<Product> list = productService.getAllProducts();

        assertEquals(2, list.size());

        verify(productDao, times(1)).findAll();
    }

    @Test
    void createProduct() {

        when(productDao.save(firstProduct)).thenReturn(firstProduct);

        productService.createProduct(firstProduct);

        verify(productDao, times(1)).save(firstProduct);
    }

    @Test
    void getProductById() {

        when(productDao.findByProductId(ID)).thenReturn(firstProduct);

        Product productForVerify = productService.getProductById(ID);

        assertEquals(firstProduct.getProductId(), productForVerify.getProductId());

        verify(productDao, times(1)).findByProductId(ID);
    }

    @Test
    void updateProductById() {

        when(productDao.findById(ID)).thenReturn(Optional.of(firstProduct));
        when(productDao.save(firstProduct)).thenReturn(firstProduct);

        Product updatedProduct = productService.updateProductById(secondProduct, ID);

        assertNotNull(updatedProduct);
        assertEquals(firstProduct.getProductId(), updatedProduct.getProductId());
        assertEquals(firstProduct.getName(), updatedProduct.getName());
        assertEquals(firstProduct.getProductId(), updatedProduct.getProductId());
        assertEquals(firstProduct.getQuantity(), updatedProduct.getQuantity());

        verify(productDao, times(1)).save(updatedProduct);
    }

    @Test
    void deleteProductById() {

        doNothing().when(productDao).deleteById(anyLong());

        productService.deleteProductById(ID);

        verify(productDao, times(1)).deleteById(ID);
    }
}