package com.healthportal.services;

import com.healthportal.dto.CartProductDTO;
import com.healthportal.entities.CartProduct;
import com.healthportal.entities.Product;
import com.healthportal.entities.ShoppingCart;
import com.healthportal.entities.User;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.CartProductRepository;
import com.healthportal.repositories.ProductRepository;
import com.healthportal.repositories.ShoppingCartRepository;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductService {

    @Inject
    private CartProductRepository cartProductRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    ShoppingCartRepository shoppingCartRepository;

    public CartProductDTO findByCartProductId(int id){
        CartProduct cartProd = cartProductRepository.findByCartProdId(id);

        if (cartProd == null) {
            throw new ResourceNotFoundException(CartProduct.class.getSimpleName());
        }

        CartProductDTO dto = new CartProductDTO.Builder()
                             .cartProdId(id)
                             .create();
        return dto;
    }

    public List<CartProductDTO> findAll()
    {
        List<CartProduct> cartProducts = cartProductRepository.findAll();
        List<CartProductDTO> toReturn = new ArrayList<>();

        for(CartProduct cartProduct: cartProducts){
            CartProductDTO dto = new CartProductDTO.Builder()
                    .cartProdId(cartProduct.getCartProdId())
                    .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

    public void deleteCartProductById(int id){
        CartProduct cartProduct = cartProductRepository.findByCartProdId(id);
        if (cartProduct == null) {
            throw new ResourceNotFoundException(CartProduct.class.getSimpleName());
        }
        cartProductRepository.delete(cartProduct);
    }

    public CartProduct addCartProduct(int productId,int userId){
        User user = userRepository.findByUserId(userId);
        ShoppingCart shoppingCart = shoppingCartRepository.getByUser(user);
        Product product = productRepository.findByProductId(productId);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setShoppingCart(shoppingCart);

        CartProduct newCartProduct = cartProductRepository.save(cartProduct);
        return newCartProduct;
    }



}
