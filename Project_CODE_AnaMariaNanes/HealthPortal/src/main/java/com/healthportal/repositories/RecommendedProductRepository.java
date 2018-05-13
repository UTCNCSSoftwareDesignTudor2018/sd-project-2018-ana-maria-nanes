package com.healthportal.repositories;

import com.healthportal.entities.RecommendedList;
import com.healthportal.entities.RecommendedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendedProductRepository extends JpaRepository<RecommendedProduct, Integer> {

    RecommendedProduct findByRecProdId(int id);
    List<RecommendedProduct> findAll();
    void delete(RecommendedProduct recommendedProduct);
    void deleteAllByRecommendedList(RecommendedList recommendedList);
    RecommendedProduct save(RecommendedProduct recommendedProduct);
}
