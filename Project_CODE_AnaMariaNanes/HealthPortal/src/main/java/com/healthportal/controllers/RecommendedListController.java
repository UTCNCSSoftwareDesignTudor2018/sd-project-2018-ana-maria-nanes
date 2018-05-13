package com.healthportal.controllers;


import com.healthportal.dto.RecommendedListDTO;
import com.healthportal.entities.RecommendedList;
import com.healthportal.services.RecommendedListService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/recommendationList")
public class RecommendedListController {

    @Inject
    private RecommendedListService recommendedListService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<RecommendedListDTO> getAllRecommendationLists()
    {
        return recommendedListService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RecommendedListDTO getRecommendationListById(@PathVariable("id") int id)
    {
        return recommendedListService.findByRecommendationListId(id);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public RecommendedListDTO getRecommendationListByUser(@PathVariable("userId") int userId) {
        return recommendedListService.findByUser(userId);
    }

    @RequestMapping(value= "/added/{userId}", method = RequestMethod.POST)
    public RecommendedList addRecommendationList(@PathVariable("userId") int userId) {
        return recommendedListService.addRecommendationList(userId);
    }
}