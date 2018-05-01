package com.healthportal.services;

import com.healthportal.repositories.WishListRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class WishListService {

    @Inject
    private WishListRepository wishListRepository;
}
