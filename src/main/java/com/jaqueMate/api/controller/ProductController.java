package com.jaqueMate.api.controller;


import com.jaqueMate.application.service.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import main.java.com.jaqueMate.domain.model.Product;

import java.util.List;



@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;
    private final GetAllProductsService getAllProductsService;

    @Autowired
    public ProductController(CreateProductService createProductService,
                             GetProductService getProductService,
                             UpdateProductService updateProductService,
                             DeleteProductService deleteProductService,
                             GetAllProductsService getAllProductsService) {
        this.createProductService = createProductService;
        this.getProductService = getProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.getAllProductsService = getAllProductsService;
    }
    @PostMapping("/add")
    public void createProduct(@RequestBody CreateProductService.CreateProductRequest request) {
        createProductService.execute(request);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return getAllProductsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return getProductService.execute(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable DeleteProductService.DeleteProductRequest request) {
         deleteProductService.execute(request);
    }

    @PutMapping("/update/{id}")
    public void updateProductById(@PathVariable int id, @RequestBody UpdateProductService.UpdateProductRequest request) {
        updateProductService.execute(request);
    }


}
