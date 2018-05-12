package com.healthportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
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

    public void computeTotalCost(){
        float computedToatal = 0;
        for(CartProduct cartProduct: cartProducts){
            computedToatal += cartProduct.getTotal();
        }
        this.totalCost = computedToatal;
    }

    public void computeTotalProductNo() {
        int computedNo = 0;
        for(CartProduct cartProduct: cartProducts){
            computedNo += cartProduct.getQuantity();
        }
        this.productNo = computedNo;
    }
}
