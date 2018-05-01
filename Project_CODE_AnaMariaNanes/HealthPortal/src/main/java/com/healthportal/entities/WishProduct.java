package com.healthportal.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "wishProducts")
public class WishProduct {

    private static final long serialVersionUID = 10L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "wishProdId", unique = true, nullable = false)
    private Integer wishProdId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "wishListId")
    private WishList wishList;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productId")
    private Product product;

    public WishProduct(){

    }

    public Integer getWishProdId() {
        return wishProdId;
    }

    public void setWishProdId(Integer wishProdId) {
        this.wishProdId = wishProdId;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
