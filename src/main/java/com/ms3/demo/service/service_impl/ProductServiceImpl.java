package com.ms3.demo.service.service_impl;

import com.ms3.demo.model.dao.ProductDao;
import com.ms3.demo.model.entities.Product;
import com.ms3.demo.service.service_decl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.findByProductId(productId);
    }

    @Override
    public void updateProductById(Product productForUpdate, long productId) {

        Product product = productDao.findByProductId(productId);
        if (product != null) {
            productDao.save(productForUpdate);
        }
    }

    @Override
    public void deleteProductById(long productId) {
        productDao.delete(getProductById(productId));
    }
}
