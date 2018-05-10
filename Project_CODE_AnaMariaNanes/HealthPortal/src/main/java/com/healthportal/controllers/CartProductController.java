package com.healthportal.controllers;

import com.healthportal.dto.CartProductDTO;
import com.healthportal.entities.CartProduct;
import com.healthportal.observer.Observable;
import com.healthportal.observer.Observer;
import com.healthportal.services.CartProductService;
import com.healthportal.services.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/cartProduct")
public class CartProductController implements Observable{

    @Inject
    private CartProductService cartProductService;

    @Inject
    private ShoppingCartService shoppingCartService;

    private Observer observer = shoppingCartService;

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
        notifyObservers("removed",id);
        cartProductService.deleteCartProductById(id);
    }

    @RequestMapping(value= "/added/{productId}/user/{userId}", method = RequestMethod.POST)
    public CartProduct addCartProduct(@PathVariable("productId") int productId,@PathVariable("userId") int userId,@RequestBody CartProduct cartProduct) {
        CartProduct cartProd = cartProductService.addCartProduct(productId,userId,cartProduct);
        notifyObservers("added",cartProd.getCartProdId());
        return cartProd;
    }

    @RequestMapping(value = "/delete/shoppingCart/{id}", method = RequestMethod.DELETE)
    public void deleteByShoppingCart(@PathVariable("id") int id) {
        cartProductService.deleteByShoppingCart(id);
    }

    public void addObserver(Observer o){
        this.observer = observer;
    }

    public void removeObserver(Observer o){
    }

    public void notifyObservers(String command,int id){
            shoppingCartService.update(command,id);
    }

}
