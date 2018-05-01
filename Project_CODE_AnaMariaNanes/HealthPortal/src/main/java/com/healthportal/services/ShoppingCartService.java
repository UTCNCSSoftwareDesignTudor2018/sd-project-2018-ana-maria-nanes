package com.healthportal.services;

import com.healthportal.dto.ShoppingCartDTO;
import com.healthportal.dto.UserDTO;
import com.healthportal.entities.ShoppingCart;
import com.healthportal.entities.User;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.ShoppingCartRepository;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ShoppingCartService {

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    @Inject
    private UserRepository userRepository;

    public ShoppingCartDTO findByCartlId(int id){
        ShoppingCart shoppingCart = shoppingCartRepository.getByCartId(id);

        if(shoppingCart == null){
            throw new ResourceNotFoundException(ShoppingCart.class.getSimpleName());
        }

        ShoppingCartDTO dto = new ShoppingCartDTO.Builder()
                .cartId(shoppingCart.getCartId())
                .productNo(shoppingCart.getProductNo())
                .totalCost(shoppingCart.getTotalCost())
                .create();

        return dto;
    }

    public ShoppingCartDTO findByUser(User user){
        ShoppingCart shoppingCart = shoppingCartRepository.getByUser(user);

        if(shoppingCart == null){
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

    public ShoppingCart addShoppingCart(int userId,ShoppingCart shoppingCart){

         if(shoppingCart == null){
             throw new ResourceNotFoundException(ShoppingCart.class.getSimpleName());
         }
         shoppingCart.setUser(userRepository.findByUserId(userId));

         ShoppingCart newCart = shoppingCartRepository.save(shoppingCart);
         return newCart;
    }

    public ShoppingCart updateShoppingCart(int userId,ShoppingCart shoppingCart){

        if(shoppingCart == null){
            throw new ResourceNotFoundException(ShoppingCart.class.getSimpleName());
        }

        ShoppingCart initialCart = shoppingCartRepository.getByUser(userRepository.findByUserId(userId));
        initialCart.setUser(userRepository.findByUserId(userId));
        initialCart.setProductNo(shoppingCart.getProductNo());
        initialCart.setTotalCost(shoppingCart.getTotalCost());

        ShoppingCart updated = shoppingCartRepository.save(initialCart);
        return updated;
    }


}
