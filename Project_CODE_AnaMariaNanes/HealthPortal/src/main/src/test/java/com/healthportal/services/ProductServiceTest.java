package com.healthportal.services;

import com.healthportal.dto.ProductDTO;
import com.healthportal.entities.Product;
import com.healthportal.repositories.ProductRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest {
    @Test
    public void findAllByType() throws Exception {
        ProductService test =  Mockito.mock(ProductService.class);

        List<ProductDTO> allFruits = test.findAllByType("fruit");

        assertEquals(allFruits.size(), 3);
    }

    @Test
    public void deleteProductById() throws Exception {
        ProductService test =  Mockito.mock(ProductService.class);
        int countBeforeDelete = test.findAll().size();
        test.deleteProductById(3);
        int countAfterDetele = test.findAll().size();

        assertEquals(countAfterDetele, countBeforeDelete -1);
    }

    @Test
    public void updateProduct() throws Exception {
        ProductService test =  Mockito.mock(ProductService.class);
        ProductRepository testRepo = Mockito.mock(ProductRepository.class);

        Product newProduct = testRepo.findByProductId(1);
        int oldStock = newProduct.getStock();
        int newStock = oldStock + 10;
        newProduct.setStock(newStock);

        test.updateProduct(1,newProduct);
        ProductDTO retrievedProductDto = test.findByProductId(1);

        assertEquals(retrievedProductDto.getStock(), newStock);

    }

}