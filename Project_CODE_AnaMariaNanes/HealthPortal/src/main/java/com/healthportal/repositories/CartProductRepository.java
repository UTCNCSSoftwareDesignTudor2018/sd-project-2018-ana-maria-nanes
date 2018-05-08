package com.healthportal.repositories;

import com.healthportal.entities.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

    CartProduct findByCartProdId(int id);
    List<CartProduct> findAll();
    void delete(CartProduct  cartProduct);
    CartProduct save(CartProduct cartProduct);
}
