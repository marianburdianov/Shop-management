package com.ms3.demo.service.service_decl;

import com.ms3.demo.model.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product getProductById(long productId);

    Product updateProductById(Product product, long productId);

    void deleteProductById(long productId);

}