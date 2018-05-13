package com.healthportal.services;

import com.healthportal.dto.RecommendedListDTO;
import com.healthportal.dto.WishListDTO;
import com.healthportal.entities.RecommendedList;
import com.healthportal.entities.User;
import com.healthportal.entities.WishList;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.RecommendedListRepository;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendedListService {

    @Inject
    private RecommendedListRepository recommendedListRepository;

    @Inject
    private UserRepository userRepository;

    public RecommendedListDTO findByRecommendationListId(int id) {
        RecommendedList recommendedList = recommendedListRepository.getByRecommendedListId(id);
        if (recommendedList == null) {
            throw new ResourceNotFoundException(RecommendedList.class.getSimpleName());
        }

        RecommendedListDTO dto = new RecommendedListDTO.Builder()
                .recommmendedId(recommendedList.getRecommendedListId())
                .create();
        return dto;
    }

    public RecommendedListDTO findByUser(int userId) {
        User user = userRepository.findByUserId(userId);
        RecommendedList recommendedList = recommendedListRepository.getByUser(user);

        if (recommendedList == null) {
            throw new ResourceNotFoundException(RecommendedList.class.getSimpleName());
        }

        RecommendedListDTO dto = new RecommendedListDTO.Builder()
                .recommmendedId(recommendedList.getRecommendedListId())
                .create();
        return dto;

    }

    public List<RecommendedListDTO> findAll() {
        List<RecommendedList> recommendedLists = recommendedListRepository.findAll();
        List<RecommendedListDTO> toReturn = new ArrayList<>();

        for(RecommendedList recommendedList: recommendedLists){
            RecommendedListDTO dto = new RecommendedListDTO.Builder()
                    .recommmendedId(recommendedList.getRecommendedListId())
                    .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

    public RecommendedList addRecommendationList(int userId) {

        RecommendedList recommendedList = new RecommendedList();
        User user = userRepository.findByUserId(userId);
        recommendedList.setUser(user);

        RecommendedList newRecommendationList = recommendedListRepository.save(recommendedList);
        return newRecommendationList;
    }
}
