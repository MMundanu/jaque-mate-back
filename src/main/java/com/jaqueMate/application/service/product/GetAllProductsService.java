package com.jaqueMate.application.service.product;

import main.java.com.jaqueMate.domain.port.ProductRepository;
import main.java.com.jaqueMate.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsService {
    private final ProductRepository productRepository;

    @Autowired
    public GetAllProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

}
