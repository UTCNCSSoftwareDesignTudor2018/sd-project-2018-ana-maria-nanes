package com.healthportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    @OneToMany(mappedBy = "wishList", cascade = CascadeType.REMOVE)
    private List<WishListProduct> wishProducts;

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

    public List<WishListProduct> getWishProducts() {
        return wishProducts;
    }

    public void setWishProducts(List<WishListProduct> wishProducts) {
        this.wishProducts = wishProducts;
    }
}
