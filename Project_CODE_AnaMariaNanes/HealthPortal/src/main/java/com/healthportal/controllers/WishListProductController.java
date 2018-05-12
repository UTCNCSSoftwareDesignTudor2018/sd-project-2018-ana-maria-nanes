package com.healthportal.controllers;

import com.healthportal.dto.WishListProductDTO;
import com.healthportal.entities.WishListProduct;
import com.healthportal.services.WishListProductService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/wishListProduct")
public class WishListProductController {

    @Inject
    private WishListProductService wishListProductService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<WishListProductDTO> getAllWishListProducts()

    {
        return wishListProductService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WishListProductDTO getWishListProductById(@PathVariable("id") int id)
    {
        return wishListProductService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        wishListProductService.deleteById(id);
    }

    @RequestMapping(value = "/wishList/{wishListId}", method = RequestMethod.DELETE)
    public void deleteByWishList(@PathVariable("wishListId") int wishListId) {
        wishListProductService.deleteByWishList(wishListId);
    }

    @RequestMapping(value= "/added/{productId}/user/{userId}", method = RequestMethod.POST)
    public WishListProduct addWishListProduct(@PathVariable("productId") int productId, @PathVariable("userId") int userId,@RequestBody WishListProduct wishListProduct) {
        WishListProduct wishListProd= wishListProductService.addWishListProduct(productId,userId,wishListProduct);
        return wishListProd;
    }

}
