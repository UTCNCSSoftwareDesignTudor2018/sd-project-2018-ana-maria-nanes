package com.healthportal.services;

import com.healthportal.dto.ProductDTO;
import com.healthportal.dto.WishListProductDTO;
import com.healthportal.entities.*;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.ProductRepository;
import com.healthportal.repositories.UserRepository;
import com.healthportal.repositories.WishListProductRepository;
import com.healthportal.repositories.WishListRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WishListProductService {

    @Inject
    private WishListProductRepository wishListProductRepository;

    @Inject
    private ProductService productService;

    @Inject
    private UserRepository userRepository;

    @Inject
    private WishListRepository wishListRepository;

    @Inject
    private ProductRepository productRepository;

    public WishListProductDTO findById(int id){
        WishListProduct wishProd = wishListProductRepository.findByWishProdId(id);
        int productId = wishProd.getProduct().getProductId();
        ProductDTO productDTO = productService.findByProductId(productId);

        if(wishProd == null){
            throw new ResourceNotFoundException(WishListProduct.class.getSimpleName());
        }

        WishListProductDTO dto = new WishListProductDTO.Builder()
                                .wishProdId(wishProd.getWishProdId())
                                .productDTO(productDTO)
                                .create();
        return dto;
    }

    public List<WishListProductDTO> findAll(){
        List<WishListProduct> wishProducts = wishListProductRepository.findAll();
        List<WishListProductDTO> toReturn = new ArrayList<>();

        for(WishListProduct wishListProduct: wishProducts){
            int productId = wishListProduct.getProduct().getProductId();
            ProductDTO productDTO = productService.findByProductId(productId);

            WishListProductDTO dto = new WishListProductDTO.Builder()
                    .wishProdId(wishListProduct.getWishProdId())
                    .productDTO(productDTO)
                    .create();
            toReturn.add(dto);

        }
        return toReturn;
    }

    public void deleteById(int id){
        WishListProduct wishListProduct = wishListProductRepository.findByWishProdId(id);
        if (wishListProduct == null) {
            throw new ResourceNotFoundException(WishListProduct.class.getSimpleName());
        }
        wishListProductRepository.delete(wishListProduct);
    }

    public void deleteByWishList(int wishListId){
        WishList wishList = wishListRepository.getByWishListId(wishListId);
        wishListProductRepository.deleteAllByWishList(wishList);
    }

    public WishListProduct addWishListProduct(int productId,int userId,WishListProduct wishListProduct){
        User user = userRepository.findByUserId(userId);
        WishList wishList = wishListRepository.getByUser(user);
        List<WishListProduct> wishListProducts = wishList.getWishProducts();

            for(WishListProduct wishListProd : wishListProducts){
                if(wishListProd.getProduct().getProductId() == productId){
                       return wishListProd;
                }
        }
        Product product = productRepository.findByProductId(productId);
        wishListProduct.setProduct(product);
        wishListProduct.setWishList(wishList);
        WishListProduct newWishListProduct = wishListProductRepository.save(wishListProduct);
        return newWishListProduct;
    }
}
