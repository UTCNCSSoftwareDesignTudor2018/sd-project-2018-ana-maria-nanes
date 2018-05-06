package com.healthportal.services;

import com.healthportal.dto.ProductDTO;
import com.healthportal.entities.Product;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public ProductDTO findByProductId(int id){
        Product product = productRepository.findByProductId(id);

        if (product == null) {
            throw new ResourceNotFoundException(Product.class.getSimpleName());
        }

        ProductDTO dto =new ProductDTO.Builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .type(product.getType())
                .benefits(product.getBenefits())
                .distributor(product.getDistributor())
                .price(product.getPrice())
                .stock(product.getStock())
                .readMoreLink(product.getReadMoreLink())
                .diseaseList(product.getDiseaseList())
                .create();

        return dto;
    }

    public ProductDTO findByProductName(String name){
        Product product = productRepository.findByProductName(name);

        if (product == null) {
            throw new ResourceNotFoundException(Product.class.getSimpleName());
        }

        ProductDTO dto =new ProductDTO.Builder()
                .productId(product.getProductId())
                .create();

        return dto;
    }

    public List<ProductDTO> findAll(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> toReturn = new ArrayList<>();
        for(Product product: products){
            ProductDTO dto =new ProductDTO.Builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .type(product.getType())
                    .benefits(product.getBenefits())
                    .distributor(product.getDistributor())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .readMoreLink(product.getReadMoreLink())
                    .diseaseList(product.getDiseaseList())
                    .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

     public List<ProductDTO> findAllByType(String type){
        List<Product> products = productRepository.findAllByType(type);
        List<ProductDTO> toReturn = new ArrayList<>();
        for(Product product: products){
                ProductDTO dto = new ProductDTO.Builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .type(product.getType())
                        .benefits(product.getBenefits())
                        .distributor(product.getDistributor())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .readMoreLink(product.getReadMoreLink())
                        .diseaseList(product.getDiseaseList())
                        .create();
                toReturn.add(dto);
        }
        return toReturn;
    }

    public void deleteProductById(int id){
        Product product = productRepository.findByProductId(id);
        if (product == null) {
            throw new ResourceNotFoundException(Product.class.getSimpleName());
        }
        productRepository.delete(product);

    }

    public Product addProduct(Product product){
        if (product == null) {
            throw new ResourceNotFoundException(Product.class.getSimpleName());
        }

        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    public Product updateProduct(int productId,Product product){
        if (product == null) {
            throw new ResourceNotFoundException(Product.class.getSimpleName());
        }

        Product initialProduct = productRepository.findByProductId(productId);
        initialProduct.setProductName(product.getProductName());
        initialProduct.setBenefits(product.getBenefits());
        initialProduct.setDistributor(product.getDistributor());
        initialProduct.setPrice(product.getPrice());
        initialProduct.setType(product.getType());
        initialProduct.setStock(product.getStock());
        initialProduct.setReadMoreLink(product.getReadMoreLink());
        initialProduct.setDiseaseList(product.getDiseaseList());

        Product pdr = productRepository.save(initialProduct);
        return pdr;
    }
}
