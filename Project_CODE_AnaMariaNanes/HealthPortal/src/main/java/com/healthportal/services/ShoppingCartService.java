package com.healthportal.services;

import com.healthportal.dto.ShoppingCartDTO;
import com.healthportal.dto.UserDTO;
import com.healthportal.entities.CartProduct;
import com.healthportal.entities.ShoppingCart;
import com.healthportal.entities.User;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.observer.Observer;
import com.healthportal.repositories.CartProductRepository;
import com.healthportal.repositories.ShoppingCartRepository;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService implements Observer {

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private CartProductRepository cartProductRepository;

    public ShoppingCartDTO findByCartlId(int id) {
        ShoppingCart shoppingCart = shoppingCartRepository.getByCartId(id);

        if (shoppingCart == null) {
            throw new ResourceNotFoundException(ShoppingCart.class.getSimpleName());
        }

        ShoppingCartDTO dto = new ShoppingCartDTO.Builder()
                .cartId(shoppingCart.getCartId())
                .productNo(shoppingCart.getProductNo())
                .totalCost(shoppingCart.getTotalCost())
                .create();

        return dto;
    }

    public ShoppingCartDTO findByUser(int userId) {
        User user = userRepository.findByUserId(userId);
        ShoppingCart shoppingCart = shoppingCartRepository.getByUser(user);

        if (shoppingCart == null) {
            throw new ResourceNotFoundException(ShoppingCart.class.getSimpleName());
        }

        UserDTO userDto = new UserDTO.Builder()
                .userid(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .address(user.getAddress())
                .age(user.getAge())
                .gender(user.getGender())
                .password(user.getPassword())
                .role(user.getRole())
                .create();

        ShoppingCartDTO dto = new ShoppingCartDTO.Builder()
                .cartId(shoppingCart.getCartId())
                .productNo(shoppingCart.getProductNo())
                .totalCost(shoppingCart.getTotalCost())
                .userDto(userDto)
                .create();

        return dto;
    }

    public List<ShoppingCartDTO> findAll() {
        List<ShoppingCart> carts = shoppingCartRepository.findAll();
        List<ShoppingCartDTO> toReturn = new ArrayList<>();
        for (ShoppingCart shoppingCart : carts) {
            ShoppingCartDTO dto = new ShoppingCartDTO.Builder()
                    .cartId(shoppingCart.getCartId())
                    .productNo(shoppingCart.getProductNo())
                    .totalCost(shoppingCart.getTotalCost())     //add userDto ???
                    .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

    public ShoppingCart addShoppingCart(int userId, ShoppingCart shoppingCart) {

        if (shoppingCart == null) {
            throw new ResourceNotFoundException(ShoppingCart.class.getSimpleName());
        }
        shoppingCart.setUser(userRepository.findByUserId(userId));

        ShoppingCart newCart = shoppingCartRepository.save(shoppingCart);
        return newCart;
    }

    public ShoppingCart updateShoppingCart(int userId, ShoppingCart shoppingCart) {

        if (shoppingCart == null) {
            throw new ResourceNotFoundException(ShoppingCart.class.getSimpleName());
        }

        ShoppingCart initialCart = shoppingCartRepository.getByUser(userRepository.findByUserId(userId));
        initialCart.setUser(userRepository.findByUserId(userId));
        initialCart.setProductNo(shoppingCart.getProductNo());
        initialCart.setTotalCost(shoppingCart.getTotalCost());

        ShoppingCart updated = shoppingCartRepository.save(initialCart);
        return updated;
    }

    public void update(String command,int id, int quantity, float total) {
        CartProduct  cartProduct = cartProductRepository.findByCartProdId(id);
        ShoppingCart shoppingCart = cartProduct.getShoppingCart();
        List<CartProduct> cartProducts = shoppingCart.getCartProducts();

        float finalTotal = 0;
        int countProducts =0;

        for(CartProduct cartProd : cartProducts){
           countProducts += cartProd.getQuantity();
           finalTotal += cartProd.getTotal();
        }

        boolean found = false;
        for(CartProduct cartProd : cartProducts){
            if(cartProd.getProduct().getProductId() == cartProduct.getProduct().getProductId()){
                found = true;
            }
        }

        if(command.equals("added")) {
            if(found == false){
            finalTotal += total;
            countProducts += quantity;}
        }else
        {
            finalTotal -= cartProduct.getTotal();
            countProducts -= cartProduct.getQuantity();
        }

        shoppingCart.setProductNo(countProducts);
        shoppingCart.setTotalCost(finalTotal);

        ShoppingCart updated = shoppingCartRepository.save(shoppingCart);
    }
}
