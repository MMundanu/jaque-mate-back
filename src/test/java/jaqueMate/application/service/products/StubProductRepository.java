package jaqueMate.application.service.products;

import main.java.com.jaqueMate.domain.port.ProductRepository;
import main.java.com.jaqueMate.domain.model.Product;

import java.util.*;


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

    @Override
    public void reduceStock(int productId, int stockToReduce) {
        Optional<Product> product = Optional.ofNullable(products.get(productId));
        if (product.isPresent()){
            int toReduce = product.get().getStock() - stockToReduce;
             product.get().setStock(toReduce);
        }
    }

    @Override
    public void addStock(int productId, int stockToAdd) {
        Optional<Product> product = Optional.ofNullable(products.get(productId));
        if (product.isPresent()){
            int toAdd = product.get().getStock() + stockToAdd;
            product.get().setStock(toAdd);
            products.put(productId, product.get());
        }
    }


}
