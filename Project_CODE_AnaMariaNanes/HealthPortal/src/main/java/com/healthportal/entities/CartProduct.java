package com.healthportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cartProducts")
public class CartProduct implements Serializable{

    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cartProdId", unique = true, nullable = false)
    private Integer cartProdId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "cartId")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productId")
    private Product product;

    public CartProduct(){

    }

    public Integer getCartProdId() {
        return cartProdId;
    }

    public void setCartProdId(Integer cartProdId) {
        this.cartProdId = cartProdId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
