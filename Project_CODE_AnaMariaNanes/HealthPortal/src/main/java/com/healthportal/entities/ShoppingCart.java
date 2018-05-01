package com.healthportal.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "shoppingCarts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cartId", unique = true, nullable = false)
    private Integer cartId;

    private int productNo = 0;
    private float totalCost = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.REMOVE)
    private List<CartProduct> cartProducts;

    public ShoppingCart(){

    }

    public ShoppingCart(int cartId,int productNo, float totalCost) {
        this.cartId = cartId;
        this.productNo = productNo;
        this.totalCost = totalCost;
        this.cartProducts = new ArrayList<>();
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
