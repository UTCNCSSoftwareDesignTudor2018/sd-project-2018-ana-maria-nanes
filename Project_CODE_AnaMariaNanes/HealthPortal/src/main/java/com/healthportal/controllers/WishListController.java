package com.healthportal.controllers;

import com.healthportal.dto.WishListDTO;
import com.healthportal.entities.WishList;
import com.healthportal.services.WishListService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/wishList")
public class WishListController {

    @Inject
    private WishListService wishListService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<WishListDTO> getAllWishLists()
    {
        return wishListService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WishListDTO getWishListById(@PathVariable("id") int id)
    {
        return wishListService.findByWishListId(id);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public WishListDTO getWishListUser(@PathVariable("userId") int userId) {
        return wishListService.findByUser(userId);
    }

    @RequestMapping(value= "/added/{userId}", method = RequestMethod.POST)
    public WishList addWishList(@PathVariable("userId") int userId) {
        return wishListService.addWishList(userId);
    }

}
