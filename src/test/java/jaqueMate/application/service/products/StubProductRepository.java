package jaqueMate.application.service.products;

import main.java.com.jaqueMate.domain.port.ProductRepository;
import main.java.com.jaqueMate.domain.model.Product;

import java.util.*;
import java.util.stream.Collectors;

public class StubProductRepository implements ProductRepository {
    private final Map<Integer, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void update(Product product){
        products.put(product.getId(), product);
    }

    @Override
    public void delete(Product product){
        products.remove(product.getId());
    }

    @Override
    public Optional<Product> getProductById(int id){
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> getProducts(){
        return new ArrayList<>(products.values());
    }

}
