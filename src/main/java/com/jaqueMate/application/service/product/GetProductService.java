package com.jaqueMate.application.service.product;

import com.jaqueMate.domain.exceptions.NotFoundException;
import main.java.com.jaqueMate.domain.port.ProductRepository;
import main.java.com.jaqueMate.domain.model.Product;


import java.util.Optional;


public class GetProductService {
    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product execute(int id){
        Optional<Product> product = productRepository.getProductById(id);
        if (product.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        return product.get();
    }
}
