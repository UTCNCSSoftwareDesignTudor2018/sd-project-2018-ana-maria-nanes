package com.healthportal.controllers;

import com.healthportal.services.ShoppingCartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/health-portal/hoppingCart")
public class ShoppingCartController {

    @Inject
    private ShoppingCartService shoppingCartService;

}
