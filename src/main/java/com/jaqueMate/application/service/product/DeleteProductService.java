package com.jaqueMate.application.service.product;

import com.jaqueMate.domain.exceptions.NotFoundException;
import main.java.com.jaqueMate.domain.port.ProductRepository;
import main.java.com.jaqueMate.domain.model.Product;
import java.util.Optional;

public class DeleteProductService {
    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void execute(DeleteProductRequest request){
        Optional<Product> product = productRepository.getProductById(request.getProductId());
        if(product.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        productRepository.delete(product.get());
    }

    public static class DeleteProductRequest {
        private final int productId;
        public DeleteProductRequest(int productId) {
            this.productId = productId;
        }
        public int getProductId() { return this.productId; }
    }
}
