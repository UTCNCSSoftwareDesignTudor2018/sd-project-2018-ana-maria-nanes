package com.healthportal.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "recommendedLists")
public class RecommendedList {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "recommendedListId", unique = true, nullable = false)
    private Integer recommendedListId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "recommendedList", cascade = CascadeType.REMOVE)
    private List<RecommendedProduct> recommendedProducts;

    public RecommendedList(){

    }

    public RecommendedList(int recommendedListId){
        this.recommendedListId = recommendedListId;
        this.recommendedProducts = new ArrayList();
    }

    public Integer getRecommendedListId() {
        return recommendedListId;
    }

    public void setRecommendedListId(Integer recommendedListId) {
        this.recommendedListId = recommendedListId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RecommendedProduct> getRecommendedProducts() {
        return recommendedProducts;
    }

    public void setRecommendedProducts(List<RecommendedProduct> recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }
}
