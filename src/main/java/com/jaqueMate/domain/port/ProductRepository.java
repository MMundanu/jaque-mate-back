package main.java.com.jaqueMate.domain.port;


import main.java.com.jaqueMate.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    Optional<Product> getProductById(int id);
    List<Product> getProducts();
    void reduceStock(int productId, int stockToReduce);
    void addStock(int productId, int stockToAdd);
}
