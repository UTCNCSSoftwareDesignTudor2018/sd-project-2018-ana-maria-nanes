package com.healthportal.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "wishLists")
public class WishList {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "wishListId", unique = true, nullable = false)
    private Integer wishListId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "wishList", cascade = CascadeType.REMOVE)
    private List<WishProduct> wishProducts;

    public WishList(){

    }

    public WishList(int wishListId){
        this.wishListId = wishListId;
    }

    public Integer getWishListId() {
        return wishListId;
    }

    public void setWishListId(Integer wishListId) {
        this.wishListId = wishListId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WishProduct> getWishProducts() {
        return wishProducts;
    }

    public void setWishProducts(List<WishProduct> wishProducts) {
        this.wishProducts = wishProducts;
    }
}
