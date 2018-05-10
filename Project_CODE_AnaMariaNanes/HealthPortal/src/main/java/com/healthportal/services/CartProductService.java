package com.healthportal.services;

import com.healthportal.dto.CartProductDTO;
import com.healthportal.dto.ProductDTO;
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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartProductService {

    @Inject
    private CartProductRepository cartProductRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    ShoppingCartRepository shoppingCartRepository;

    @Inject
    ProductService productService;

    @Inject
    private ShoppingCartService shoppingCartService;

    public CartProductDTO findByCartProductId(int id){
        CartProduct cartProd = cartProductRepository.findByCartProdId(id);
        int productId = cartProd.getProduct().getProductId();
        ProductDTO productDTO = productService.findByProductId(productId);

        if (cartProd == null) {
            throw new ResourceNotFoundException(CartProduct.class.getSimpleName());
        }

        CartProductDTO dto = new CartProductDTO.Builder()
                             .cartProdId(id)
                             .total(cartProd.getTotal())
                             .quantity(cartProd.getQuantity())
                             .productDTO(productDTO)
                             .create();
        return dto;
    }

    public List<CartProductDTO> findAll()
    {
        List<CartProduct> cartProducts = cartProductRepository.findAll();
        List<CartProductDTO> toReturn = new ArrayList<>();

        for(CartProduct cartProduct: cartProducts){
            int productId = cartProduct.getProduct().getProductId();
            ProductDTO productDTO = productService.findByProductId(productId);

            CartProductDTO dto = new CartProductDTO.Builder()
                     .cartProdId(cartProduct.getCartProdId())
                     .quantity(cartProduct.getQuantity())
                     .total(cartProduct.getTotal())
                     .productDTO(productDTO)
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

    public CartProduct addCartProduct(int productId,int userId,CartProduct cartProduct){
        User user = userRepository.findByUserId(userId);
        ShoppingCart shoppingCart = shoppingCartRepository.getByUser(user);
        Product product = productRepository.findByProductId(productId);

        cartProduct.setProduct(product);
        cartProduct.setTotal(cartProduct.getQuantity() * product.getPrice());
        cartProduct.setShoppingCart(shoppingCart);

        CartProduct newCartProduct = cartProductRepository.save(cartProduct);
        return newCartProduct;
    }

    public void deleteByShoppingCart(int shoppingCartId)
    {
        ShoppingCart shoppingCart = shoppingCartRepository.getByCartId(shoppingCartId);

        //update the stock for each product

        List<CartProduct> cartProducts = shoppingCart.getCartProducts();
        for(CartProduct cartProduct : cartProducts){
            int oldStock = cartProduct.getProduct().getStock();
            int newStock = oldStock - cartProduct.getQuantity();
            cartProduct.getProduct().setStock(newStock);
        }

        cartProductRepository.deleteAllByShoppingCart(shoppingCart);
        shoppingCart.setTotalCost(0);
        shoppingCart.setProductNo(0);

    }

}
