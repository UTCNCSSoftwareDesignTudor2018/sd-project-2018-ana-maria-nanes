package com.healthportal.repositories;


import com.healthportal.entities.WishList;
import com.healthportal.entities.WishListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface WishListProductRepository extends JpaRepository<WishListProduct, Integer> {

    WishListProduct findByWishProdId(int id);
    List<WishListProduct> findAll();
    void delete(WishListProduct wishListProduct);
    WishListProduct save(WishListProduct wishListProduct);
    void deleteAllByWishList(WishList wishList);
}
