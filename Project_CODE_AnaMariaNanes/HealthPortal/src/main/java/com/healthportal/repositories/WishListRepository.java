package com.healthportal.repositories;

import com.healthportal.entities.User;
import com.healthportal.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    public WishList getByWishListId(int id);
    public WishList getByUser(User user);
    public void delete(WishList wishList);
    public WishList save(WishList wishList);
}
