package com.healthportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
public class User implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "userId", unique = true, nullable = false)
    private Integer userId;
    private String username;
    private String name;
    private String password;
    private String address;
    private int age;
    private String gender;
    private String role;

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserDisease> userDiseases;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "user")
    private ShoppingCart shoppingCart;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "user")
    private WishList wishList;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "user")
    private RecommendedList recommendedList;

    public User() {

    }

    public User(Integer userId, String username, String name, String password, String address, int age, String gender, String role) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.userDiseases = new ArrayList<>();
    }
    public User( String username, String name, String password, String address, int age, String gender, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.userDiseases = new ArrayList<>();
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserDisease> getUserDiseases() {
        return userDiseases;
    }

    public void setUserDiseases(List<UserDisease> userDiseases) {
        this.userDiseases = userDiseases;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public RecommendedList getRecommendedList() {
        return recommendedList;
    }

    public void setRecommendedList(RecommendedList recommendedList) {
        this.recommendedList = recommendedList;
    }
}
