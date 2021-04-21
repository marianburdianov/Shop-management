package com.ms3.demo.controller;

import com.ms3.demo.model.entities.Product;
import com.ms3.demo.service.service_decl.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
@Api(value = "product", description = "CRUD Operations for Product", tags = "PRODUCT")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    @ApiOperation(value = "GET ALL PRODUCTS", notes = "\n" + "This operation gets all products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    @ApiOperation(value = "GET PRODUCT BY ID", notes = "\n" + "This operation get product by id")
    public Product getProductById(@PathVariable Long productId) {

        return productService.getProductById(productId);
    }

    @PostMapping("/")
    @ApiOperation(value = "CREATE CUSTOMER", notes = "\n" + "This operation creates a customer")
    public Product addProduct(@RequestBody Product product) {

        productService.createProduct(product);

        return product;
    }

    @PutMapping("/{productId}")
    @ApiOperation(value = "UPDATE PRODUCT", notes = "\n" + "This operation updates a existing product")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long productId) {

        productService.updateProductById(product, productId);
        return product;
    }

    @DeleteMapping("/{productId}")
    @ApiOperation(value = "DELETE PRODUCT BY ID", notes = "\n" + "This operation delete product by id")
    public void deleteProductById(@PathVariable Long productId) {

        productService.deleteProductById(productId);
    }
}
