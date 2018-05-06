package com.healthportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "recommendedProducts")
public class RecommendedProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "recProdId", unique = true, nullable = false)
    private Integer recProdId;

    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recommendedListId")
    private RecommendedList recommendedList;

    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productId")
    private Product product;

    public RecommendedProduct(){

    }

    public Integer getRecProdId() {
        return recProdId;
    }

    public void setRecProdId(Integer recProdId) {
        this.recProdId = recProdId;
    }

    public RecommendedList getRecommendedList() {
        return recommendedList;
    }

    public void setRecommendedList(RecommendedList recommendedList) {
        this.recommendedList = recommendedList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
