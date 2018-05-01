package com.healthportal.services;

import com.healthportal.repositories.RecommendedListRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RecommendedListService {

    @Inject
    private RecommendedListRepository recommendedListRepository;
}
