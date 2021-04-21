package com.ms3.demo.controller;

import com.ms3.demo.model.entities.Product;
import com.ms3.demo.service.service_decl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {

        return productService.getProductById(productId);
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {

        productService.createProduct(product);

        return product;
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long productId) {

        productService.updateProductById(product, productId);
        return product;
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId) {

        productService.deleteProductById(productId);
    }
}
