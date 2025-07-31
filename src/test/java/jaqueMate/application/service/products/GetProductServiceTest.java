package jaqueMate.application.service.products;

import com.jaqueMate.application.service.product.GetProductService;
import com.jaqueMate.application.service.product.UpdateProductService;
import com.jaqueMate.domain.exceptions.NotFoundException;
import main.java.com.jaqueMate.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GetProductServiceTest {
    @Test
    public void getProduct() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);
        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        GetProductService service = new GetProductService(productRepository);

        Product productFind = service.execute(product.getId());

        assertEquals(productFind.getName(), product.getName());
        assertEquals(productFind.getDescription(), product.getDescription());
        assertEquals(productFind.getPrice(), product.getPrice());
        assertEquals(productFind.getCategoryId(), product.getCategoryId());
        assertEquals(productFind.getImage(), product.getImage());
        assertEquals(productFind.getStock(), product.getStock());

    }
    @Test
    void shouldThrowExceptionWhenNotFoundProduct() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        GetProductService service = new GetProductService(productRepository);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(5000)
        );
        assertEquals("Product not found", exception.getMessage());


    }

}
