package com.ms3.demo.model.dao;

import com.ms3.demo.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    Product findByProductId(Long id);
}
