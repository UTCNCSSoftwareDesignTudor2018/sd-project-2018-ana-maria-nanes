package com.healthportal.controllers;

import com.healthportal.dto.ShoppingCartDTO;
import com.healthportal.entities.ShoppingCart;
import com.healthportal.services.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/shoppingCart")
public class ShoppingCartController {

    @Inject
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ShoppingCartDTO> getAllCarts() {
        return shoppingCartService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ShoppingCartDTO getCartById(@PathVariable("id") int id) {
        return shoppingCartService.findByCartlId(id);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ShoppingCartDTO getCartUser(@PathVariable("userId") int userId) {
        return shoppingCartService.findByUser(userId);
    }

    @RequestMapping(value= "/added/{userId}", method = RequestMethod.POST)
    public ShoppingCart addCart(@PathVariable("userId") int userId, @RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.addShoppingCart(userId,shoppingCart);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ShoppingCart updateShoppingCart(@PathVariable("id") int id,@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.updateShoppingCart(id,shoppingCart);
    }
}
