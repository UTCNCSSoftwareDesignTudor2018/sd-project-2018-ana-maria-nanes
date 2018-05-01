package com.healthportal.repositories;

import com.healthportal.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(int id);
    Product findByProductName(String name);
    List<Product> findAll();
    void delete(Product product);
    Product save(Product product);
}
