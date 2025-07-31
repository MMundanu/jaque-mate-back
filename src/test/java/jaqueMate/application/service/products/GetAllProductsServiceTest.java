package jaqueMate.application.service.products;

import com.jaqueMate.application.service.product.GetAllProductsService;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.Product;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

public class GetAllProductsServiceTest {
    @Test
    public void getAllProducts(){
        Product product1 = new Product("mate1", "mate de cuero1", 100, 1, "foto.jpg", 1);
        Product product2 = new Product("mate2", "mate de cuero2", 100, 1, "foto.jpg", 1);
        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product1);
        productRepository.save(product2);
        GetAllProductsService service = new GetAllProductsService(productRepository);

        List<Product> products = service.getAllProducts();

        assertEquals(2, products.size());

        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));

        assertIterableEquals(List.of(product1, product2), products);



    }
}
