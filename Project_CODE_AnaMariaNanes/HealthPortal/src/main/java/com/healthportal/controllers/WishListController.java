package com.healthportal.controllers;

import com.healthportal.repositories.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.inject.Inject;

@Controller
@CrossOrigin(origins = "*")
public class WishListController {

    @Inject
    private WishListRepository wishListRepository;
}
