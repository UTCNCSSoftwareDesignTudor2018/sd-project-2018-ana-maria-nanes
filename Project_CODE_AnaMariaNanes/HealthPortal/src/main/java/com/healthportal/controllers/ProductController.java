package com.healthportal.controllers;
;
import com.healthportal.dto.ProductDTO;
import com.healthportal.entities.Product;
import com.healthportal.services.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*")                      //in order to allow to post on other localhost port
@RequestMapping("/health-portal/product")
public class ProductController {

    @Inject
    private ProductService productService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }

    @RequestMapping(value = "/fruit/all", method = RequestMethod.GET)
    public List<ProductDTO> getAllFruits() {
        return productService.findAllByType("fruit");
    }

    @RequestMapping(value = "/herb/all", method = RequestMethod.GET)
    public List<ProductDTO> getAllHerbs() {
        return productService.findAllByType("herb");
    }

    @RequestMapping(value = "/vegetable/all", method = RequestMethod.GET)
    public List<ProductDTO> getAllVegetables() {
        return productService.findAllByType("vegetable");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDTO getProductById(@PathVariable("id") int id) {
        return productService.findByProductId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        productService.deleteProductById(id);
    }

    @RequestMapping(value= "/added", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        try {
            return productService.addProduct(product);
        }catch(Exception e){
            System.out.println();
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable("id") int id,@RequestBody Product product) {
        try {
            return productService.updateProduct(id, product);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
