package jaqueMate.application.service.products;

import com.jaqueMate.domain.exceptions.InvalidDataException;
import main.java.com.jaqueMate.domain.model.Product;
import com.jaqueMate.application.service.product.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class CreateProductServiceTest {
    @Test
    void createProduct() {
        StubProductRepository stub = new StubProductRepository();
        CreateProductService service = new CreateProductService(stub);
        CreateProductService.CreateProductRequest request = new CreateProductService.CreateProductRequest("Mate", "Mate de cuero", 100.0, 1, "mate.jpg", 2);

        service.execute(request);

        Optional<Product> saved = stub.getProductById(1);
        assertTrue(saved.isPresent());
        assertEquals("Mate", saved.get().getName());
    }
    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        StubProductRepository stub = new StubProductRepository();
        CreateProductService service = new CreateProductService(stub);
        CreateProductService.CreateProductRequest request = new CreateProductService.CreateProductRequest("  ", "Mate de cuero", 100.0, 1, "mate.jpg", 2);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Product name is invalid", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenDescriptionIsBlank() {
        StubProductRepository stub = new StubProductRepository();
        CreateProductService service = new CreateProductService(stub);
        CreateProductService.CreateProductRequest request = new CreateProductService.CreateProductRequest("Mate", " ", 100.0, 1, "mate.jpg", 2);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Product description is invalid", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenPriceIsInvalid() {
        StubProductRepository stub = new StubProductRepository();
        CreateProductService service = new CreateProductService(stub);
        CreateProductService.CreateProductRequest request = new CreateProductService.CreateProductRequest("Mate", "Mate de cuero", -20.0, 1, "mate.jpg", 2);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Price is invalid", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenStockIsInvalid() {
        StubProductRepository stub = new StubProductRepository();
        CreateProductService service = new CreateProductService(stub);
        CreateProductService.CreateProductRequest request = new CreateProductService.CreateProductRequest("Mate", "Mate de cuero", 20.0, 1, "mate.jpg", -2);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Stock is invalid", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenImageIsBlank() {
        StubProductRepository stub = new StubProductRepository();
        CreateProductService service = new CreateProductService(stub);
        CreateProductService.CreateProductRequest request = new CreateProductService.CreateProductRequest("Mate", "Mate de cuero", 20.0, 1, "", 2);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Stock is invalid", exception.getMessage());
    }
}
