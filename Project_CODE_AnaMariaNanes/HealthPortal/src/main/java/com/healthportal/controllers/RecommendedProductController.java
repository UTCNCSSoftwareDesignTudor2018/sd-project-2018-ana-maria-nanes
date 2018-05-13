package com.healthportal.controllers;

import com.healthportal.dto.RecommendedProductDTO;
import com.healthportal.entities.RecommendedProduct;
import com.healthportal.services.RecommendedProductService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/recommendedProduct")
public class RecommendedProductController {

    @Inject
    private RecommendedProductService recommendedProductService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<RecommendedProductDTO> getAllRecommendedProducts()
    {
        return recommendedProductService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RecommendedProductDTO getRecommendedProductById(@PathVariable("id") int id)
    {
        return recommendedProductService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        recommendedProductService.deleteById(id);
    }

    @RequestMapping(value = "/recommendedList/{recommendedListId}", method = RequestMethod.DELETE)
    public void deleteByRecommendedList(@PathVariable("recommendedListId") int recommendedListId) {
        recommendedProductService.deleteByRecommendedist(recommendedListId);
    }

    @RequestMapping(value= "/added/{productId}/user/{userId}", method = RequestMethod.POST)
    public RecommendedProduct addRecommendedProduct(@PathVariable("productId") int productId, @PathVariable("userId") int userId, @RequestBody RecommendedProduct recommendedProduct) {
        RecommendedProduct newRecProd = recommendedProductService.addRecommendedProduct(productId,userId,recommendedProduct);
        return newRecProd;
    }
}
