package com.jaqueMate.application.service.product;

import com.jaqueMate.domain.exceptions.InvalidDataException;
import main.java.com.jaqueMate.domain.model.Product;
import main.java.com.jaqueMate.domain.port.ProductRepository;

public class CreateProductService {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void execute(CreateProductRequest request){

        if (request.getName() == null || request.getName().isBlank()){
            throw new InvalidDataException("Product name is invalid");
        }
        if (request.getDescription() == null || request.getDescription().isBlank()){
            throw new InvalidDataException("Product description is invalid");
        }
        if (request.getPrice() <= 0){
            throw new InvalidDataException("Price is invalid");
        }
        if (request.getStock() < 0 ){
            throw new InvalidDataException("Stock is invalid");
        }
        if (request.getImage() == null || request.getImage().isBlank()){
            throw new InvalidDataException("Image is invalid");
        }

        Product product = new Product(request.getName(), request.getDescription(), request.getPrice(), request.getCategoryId(), request.getImage(), request.getStock());
        productRepository.save(product);

    }

    public static class CreateProductRequest {
        private final String name;
        private final String description;
        private final double price;
        private final int category_id;
        private final String image;
        private final int stock;

        public CreateProductRequest(String name, String description, double price, int category_id, String image, int stock){
            this.name = name;
            this.description = description;
            this.price = price;
            this.category_id = category_id;
            this.image = image;
            this.stock = stock;
        }


        public String getName(){
            return this.name;
        }

        public String getDescription(){
            return this.description;
        }

        public double getPrice(){
            return this.price;
        }


        public int getCategoryId(){
            return this.category_id;
        }


        public String getImage(){
            return this.image;
        }

        public int getStock(){
            return this.stock;
        }


    }

}
