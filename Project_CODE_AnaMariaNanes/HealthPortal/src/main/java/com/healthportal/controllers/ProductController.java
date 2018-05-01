package com.healthportal.controllers;

import com.healthportal.dto.DiseaseDTO;
import com.healthportal.dto.ProductDTO;
import com.healthportal.dto.UserDTO;
import com.healthportal.entities.Product;
import com.healthportal.entities.User;
import com.healthportal.services.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/health-portal/product")
public class ProductController {

    @Inject
    private ProductService productService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
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

        return productService.addProduct(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable("id") int id,@RequestBody Product product) {
        return productService.updateProduct(id,product);
    }

}
