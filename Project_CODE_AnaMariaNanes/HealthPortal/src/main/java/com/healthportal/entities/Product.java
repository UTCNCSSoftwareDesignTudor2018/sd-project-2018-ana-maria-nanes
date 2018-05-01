package com.healthportal.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "productId", unique = true, nullable = false)
    private Integer productId;

    private String productName;
    private String type;
    private String benefits;
    private String distributor;
    private float price;
    private int stock;
    private String diseaseList;
    private String readMoreLink;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<CartProduct> cartProducts;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<WishProduct> wishProducts;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<RecommendedProduct> recommendedProducts;

    public Product(){

    }

    public Product(Integer productId,String productName, String type, String benefits, String distributor, float price, int stock, String readMoreLink,String diseaseList) {
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.benefits = benefits;
        this.distributor = distributor;
        this.price = price;
        this.stock = stock;
        this.readMoreLink = readMoreLink;
        this.diseaseList = diseaseList;
        this.cartProducts = new ArrayList<>();
        this.wishProducts = new ArrayList<>();
        this.recommendedProducts = new ArrayList();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getReadMoreLink() {
        return readMoreLink;
    }

    public void setReadMoreLink(String readMoreLink) {
        this.readMoreLink = readMoreLink;
    }

    public String getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(String diseaseList) {
        this.diseaseList = diseaseList;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<WishProduct> getWishProducts() {
        return wishProducts;
    }

    public void setWishProducts(List<WishProduct> wishProducts) {
        this.wishProducts = wishProducts;
    }

    public List<RecommendedProduct> getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(List<RecommendedProduct> recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }
}
