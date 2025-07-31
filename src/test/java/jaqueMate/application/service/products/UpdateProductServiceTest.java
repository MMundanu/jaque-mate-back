package jaqueMate.application.service.products;

import com.jaqueMate.application.service.product.UpdateProductService;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.Product;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateProductServiceTest {
    @Test
    void updateProduct() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "termo", "acero", 500, 5, "fotoEditada.jpg", 10);

        service.execute(request);

        Optional<Product> update = productRepository.getProductById(product.getId());
        assertTrue(update.isPresent());
        assertEquals("termo", update.get().getName());
        assertEquals("acero", update.get().getDescription());
        assertEquals(500, update.get().getPrice());
        assertEquals(5, update.get().getCategoryId());
        assertEquals("fotoEditada.jpg", update.get().getImage());
        assertEquals(10, update.get().getStock());

    }
    @Test
    void shouldThrowExceptionWhenNotFoundProduct() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(30, "termo", "acero", 500, 5, "fotoEditada.jpg", 10);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(request)
        );
        assertEquals("Product not found", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());

    }
    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "   ", "acero", 500, 5, "fotoEditada.jpg", 10);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Product name is invalid", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());

    }
    @Test
    void shouldThrowExceptionWhenDescriptionIsBlank() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "termo", "   ", 500, 5, "fotoEditada.jpg", 10);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Product description is invalid", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());

    }
    @Test
    void shouldThrowExceptionWhenPriceIsInvalid() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "mate", "acero", -500, 5, "fotoEditada.jpg", 10);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Price is invalid", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());

    }
    @Test
    void shouldThrowExceptionWhenStockIsInvalid() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "mate", "acero", 500, 5, "fotoEditada.jpg", -10);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Stock is invalid", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());

    }
    @Test
    void shouldThrowExceptionWhenImageIsBlank() {
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "mate", "acero", 500, 5, "   ", 10);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Image is invalid", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());

    }

}
