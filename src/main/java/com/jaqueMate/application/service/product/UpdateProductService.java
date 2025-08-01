package com.jaqueMate.application.service.product;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.Categories;
import com.jaqueMate.domain.port.CategoryRepository;
import main.java.com.jaqueMate.domain.model.Product;
import main.java.com.jaqueMate.domain.port.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public UpdateProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

    }
    public void execute(UpdateProductRequest request){
        Optional<Product> oldProduct = productRepository.getProductById(request.getId());

        if(oldProduct.isEmpty()){
            throw new NotFoundException("Product not found");
        }

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

        List<Categories> categories = categoryRepository.findAll();


        boolean exist = categories.stream()
                .anyMatch(category -> category.getId() == request.getCategoryId());

        if(!exist){
            throw new InvalidDataException("Category does not exist");
        }

        Product product = oldProduct.get();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId());
        product.setImage(request.getImage());
        product.setStock(request.getStock());


        productRepository.update(product);
    }

    public static class UpdateProductRequest {
        private final int id;
        private final String name;
        private final String description;
        private final double price;
        private final int category_id;
        private final String image;
        private final int stock;

        public UpdateProductRequest(int id, String name, String description, double price, int category_id, String image, int stock){
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.category_id = category_id;
            this.image = image;
            this.stock = stock;
        }


        public int getId() { return this.id; }

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
