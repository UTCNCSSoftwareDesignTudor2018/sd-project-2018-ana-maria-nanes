package com.healthportal.validators;

import com.healthportal.entities.Product;
import com.healthportal.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ProductValidator {

    @Inject
    private ProductRepository productRepository;

    public boolean validateCreateProduct(Product product){

        if(!validateName(product.getProductName()))
            return false;
        if(!validateType(product.getType()))
            return false;
        if(!validatePrice(product.getPrice()))
            return false;
        if(!validateStock(product.getStock()))
            return false;
        return true;
    }

    public boolean validateUpdateProduct(Product product){
        if(!validateType(product.getType()))
            return false;
        if(!validatePrice(product.getPrice()))
            return false;
        if(!validateStock(product.getStock()))
            return false;
        return true;
    }

    private boolean validateType(String type){

        if(type.equals("fruit") || type.equals("vegetable") || type.equals("herb") )
            return true;
        return false;
    }

    private boolean validateName(String name){

        List<Product> products = productRepository.findAll();
        for(Product product: products){
            if(product.getProductName().equals(name)){
                return false;
            }
        }
        return true;
    }

    private boolean validatePrice(float price){

        if(price > 0)
            return true;
        return false;
    }

    private boolean validateStock(int stock){

        if(stock >= 0)
            return true;
        return false;
    }
}
