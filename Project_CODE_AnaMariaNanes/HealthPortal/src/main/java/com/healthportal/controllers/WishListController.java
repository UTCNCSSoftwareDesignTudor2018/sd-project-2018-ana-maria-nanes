package com.healthportal.controllers;

import com.healthportal.repositories.WishListRepository;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
public class WishListController {

    @Inject
    private WishListRepository wishListRepository;
}
