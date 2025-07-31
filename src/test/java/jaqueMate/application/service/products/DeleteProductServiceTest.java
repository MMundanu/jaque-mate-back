package jaqueMate.application.service.products;


import com.jaqueMate.application.service.product.DeleteProductService;
import com.jaqueMate.domain.exceptions.NotFoundException;
import main.java.com.jaqueMate.domain.model.Product;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteProductServiceTest {
    @Test
    public void deleteProduct() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);
        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        DeleteProductService service = new DeleteProductService(productRepository);
        DeleteProductService.DeleteProductRequest request = new DeleteProductService.DeleteProductRequest(product.getId());


        service.execute(request);

        Optional<Product> deleted = productRepository.getProductById(1);
        assertTrue(deleted.isEmpty());
    }
    @Test
    public void shouldThrowExceptionWhenNotFoundProduct() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);
        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        DeleteProductService service = new DeleteProductService(productRepository);
        DeleteProductService.DeleteProductRequest request = new DeleteProductService.DeleteProductRequest(100);


        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(request)
        );
        assertEquals("Product not found", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());
    }
}
