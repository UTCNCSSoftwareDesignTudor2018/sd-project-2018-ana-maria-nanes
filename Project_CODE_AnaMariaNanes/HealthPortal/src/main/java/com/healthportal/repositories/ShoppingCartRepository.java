package com.healthportal.repositories;

import com.healthportal.entities.ShoppingCart;
import com.healthportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    public ShoppingCart getByCartId(int id);
    public ShoppingCart getByUser(User user);
    public void delete(ShoppingCart shoppingCart);
    public ShoppingCart save(ShoppingCart shoppingCart);

}
