package com.healthportal.repositories;

import com.healthportal.entities.RecommendedList;
import com.healthportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendedListRepository extends JpaRepository<RecommendedList, Integer> {

    public RecommendedList getByRecommendedListId(int id);
    public RecommendedList getByUser(User user);
    public void delete(RecommendedList recommendedList);
    public RecommendedList save(RecommendedList recommendedList);
}

