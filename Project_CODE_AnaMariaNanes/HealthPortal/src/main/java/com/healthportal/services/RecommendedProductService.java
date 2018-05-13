package com.healthportal.services;

import com.healthportal.dto.ProductDTO;
import com.healthportal.dto.RecommendedProductDTO;
import com.healthportal.entities.*;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.ProductRepository;
import com.healthportal.repositories.RecommendedListRepository;
import com.healthportal.repositories.RecommendedProductRepository;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecommendedProductService {

    @Inject
    private RecommendedProductRepository recommendedProductRepository;

    @Inject
    private ProductService productService;

    @Inject
    private RecommendedListRepository recommendedListRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ProductRepository productRepository;

    public RecommendedProductDTO findById(int id){
        RecommendedProduct recommendedProduct = recommendedProductRepository.findByRecProdId(id);

        if(recommendedProduct == null) {
            throw new ResourceNotFoundException(RecommendedProduct.class.getSimpleName());
        }
        int productId = recommendedProduct.getProduct().getProductId();
        ProductDTO productDTO = productService.findByProductId(productId);

        RecommendedProductDTO dto = new RecommendedProductDTO.Builder()
                .recProdId(recommendedProduct.getRecProdId())
                .productDTO(productDTO)
                .create();

        return dto;
    }

    public List<RecommendedProductDTO> findAll()
    {
        List<RecommendedProduct> recProducts = recommendedProductRepository.findAll();
        List<RecommendedProductDTO> toReturn = new ArrayList<>();

        for(RecommendedProduct recProd: recProducts) {
            int productId = recProd.getProduct().getProductId();
            ProductDTO productDTO = productService.findByProductId(productId);

            RecommendedProductDTO dto = new RecommendedProductDTO.Builder()
                    .recProdId(recProd.getRecProdId())
                    .productDTO(productDTO)
                    .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

    public void deleteById(int id){
        RecommendedProduct recommendedProduct = recommendedProductRepository.findByRecProdId(id);

        if (recommendedProduct == null) {
            throw new ResourceNotFoundException(RecommendedProduct.class.getSimpleName());
        }
        recommendedProductRepository.save(recommendedProduct);
    }

    public void deleteByRecommendedist(int recommendedListId){
        RecommendedList recommendedList = recommendedListRepository.getByRecommendedListId(recommendedListId);
        recommendedProductRepository.deleteAllByRecommendedList(recommendedList);
    }

    public RecommendedProduct addRecommendedProduct(int productId,int userId,RecommendedProduct recommendedProduct) {
        User user = userRepository.findByUserId(userId);
        RecommendedList recommendedList = recommendedListRepository.getByUser(user);
        List<RecommendedProduct>  recProds = recommendedList.getRecommendedProducts();

        for(RecommendedProduct recProd : recProds) {
            if(recProd.getProduct().getProductId() == productId) {
               return recProd;
            }
        }

        Product product = productRepository.findByProductId(productId);
        recommendedProduct.setProduct(product);
        recommendedProduct.setRecommendedList(recommendedList);
        RecommendedProduct newRecProd = recommendedProductRepository.save(recommendedProduct);
        return newRecProd;
    }
}
