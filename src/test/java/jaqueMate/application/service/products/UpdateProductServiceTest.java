package jaqueMate.application.service.products;

import com.jaqueMate.application.service.product.UpdateProductService;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.Categories;
import jaqueMate.application.service.categories.StubCategoryRepository;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.Product;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateProductServiceTest {
    @Test
    void updateProduct() {
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        categoryRepository.save(category);
        categoryRepository.save(category2);


        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "termo", "acero", 500, category2.getId(), "fotoEditada.jpg", 10);

        service.execute(request);

        Optional<Product> update = productRepository.getProductById(product.getId());
        assertTrue(update.isPresent());
        assertEquals("termo", update.get().getName());
        assertEquals("acero", update.get().getDescription());
        assertEquals(500, update.get().getPrice());
        assertEquals(category2.getId(), update.get().getCategoryId());
        assertEquals("fotoEditada.jpg", update.get().getImage());
        assertEquals(10, update.get().getStock());

    }
    @Test
    void shouldThrowExceptionWhenNotFoundProduct() {
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        categoryRepository.save(category);
        categoryRepository.save(category2);
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(30, "termo", "acero", 500, category2.getId(), "fotoEditada.jpg", 10);

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
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        categoryRepository.save(category);
        categoryRepository.save(category2);
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "   ", "acero", 500, category2.getId(), "fotoEditada.jpg", 10);

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
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        categoryRepository.save(category);
        categoryRepository.save(category2);
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "termo", "   ", 500, category2.getId(), "fotoEditada.jpg", 10);

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
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        categoryRepository.save(category);
        categoryRepository.save(category2);
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "mate", "acero", -500, category2.getId(), "fotoEditada.jpg", 10);

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
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        categoryRepository.save(category);
        categoryRepository.save(category2);
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "mate", "acero", 500, category2.getId(), "fotoEditada.jpg", -10);

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
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        categoryRepository.save(category);
        categoryRepository.save(category2);
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "mate", "acero", 500, category2.getId(), "   ", 10);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Image is invalid", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());

    }
    @Test
    void shouldThrowExceptionWhenCategoryIsInvalid() {
        Categories category = new Categories("Mate");
        Categories category2 = new Categories("Termo");
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        categoryRepository.save(category);
        categoryRepository.save(category2);
        Product product = new Product("mate", "mate de cuero", 100, category.getId(), "foto.jpg", 1);        StubProductRepository productRepository = new StubProductRepository();
        productRepository.save(product);
        UpdateProductService service = new UpdateProductService(productRepository, categoryRepository);
        UpdateProductService.UpdateProductRequest request = new UpdateProductService.UpdateProductRequest(product.getId(), "mate", "acero", 500, 20, "foto.jpg", 10);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Category does not exist", exception.getMessage());
        Optional<Product> productExist = productRepository.getProductById(product.getId());
        assertTrue(productExist.isPresent());
        assertEquals("mate", productExist.get().getName());
        assertEquals(category.getId(), productExist.get().getCategoryId());


    }


}
