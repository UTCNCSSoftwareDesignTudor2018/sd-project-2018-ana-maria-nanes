package com.healthportal.controllers;

import com.healthportal.dto.CartProductDTO;
import com.healthportal.dto.DiseaseDTO;
import com.healthportal.entities.CartProduct;
import com.healthportal.entities.Disease;
import com.healthportal.services.CartProductService;
import com.healthportal.services.DiseaseService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/cartProduct")
public class CartProductController {

    @Inject
    CartProductService cartProductService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<CartProductDTO> getAllCartProduct()
    {
        return cartProductService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CartProductDTO getCartProductById(@PathVariable("id") int id)
    {
        return cartProductService.findByCartProductId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        cartProductService.deleteCartProductById(id);
    }

    @RequestMapping(value= "/added/{productId}/user/{userId}", method = RequestMethod.POST)
    public CartProduct addCartProduct(@PathVariable("productId") int productId,@PathVariable("userId") int userId) {
        return cartProductService.addCartProduct(productId,userId);
    }
}
