package jaqueMate.application.service.orderProducts;


import com.jaqueMate.application.service.orderProduct.ReduceOrderProductService;
import com.jaqueMate.domain.exceptions.NotFoundException;
import jaqueMate.application.service.products.StubProductRepository;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.Product;
import main.java.com.jaqueMate.domain.model.OrderProduct;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;



public class ReduceOrderProductServiceTest {
    @Test
    void reduceOrderProduct() {
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubProductRepository productRepository = new StubProductRepository();
        ReduceOrderProductService service = new ReduceOrderProductService(orderProductRepository, productRepository);
        Product product = new Product("mate", "mate de cuero", 100, 5, "foto.jpg", 8);
        productRepository.save(product);
        OrderProduct orderProduct = new OrderProduct(1, product.getId(), 5, product.getPrice());
        orderProductRepository.add(orderProduct);

        service.execute(orderProduct.getId(), 2);

        Optional<OrderProduct> reduce = orderProductRepository.getById(orderProduct.getId());
        Optional<Product> updateProduct = productRepository.getProductById(product.getId());
        assertTrue(reduce.isPresent());
        assertTrue(updateProduct.isPresent());
        assertEquals(10, updateProduct.get().getStock());
        assertEquals(3, reduce.get().getQuantity());

    }
    @Test
    void deleteOrderProduct() {
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubProductRepository productRepository = new StubProductRepository();
        ReduceOrderProductService service = new ReduceOrderProductService(orderProductRepository, productRepository);
        Product product = new Product("mate", "mate de cuero", 100, 5, "foto.jpg", 8);
        productRepository.save(product);
        OrderProduct orderProduct = new OrderProduct(1, product.getId(), 5, product.getPrice());
        orderProductRepository.add(orderProduct);

        service.execute(orderProduct.getId(), 5);

        Optional<OrderProduct> reduce = orderProductRepository.getById(orderProduct.getId());
        Optional<Product> updateProduct = productRepository.getProductById(product.getId());
        assertFalse(reduce.isPresent());
        assertTrue(updateProduct.isPresent());
        assertEquals(13, updateProduct.get().getStock());

    }
    @Test
    void shouldThrowExceptionWhenNotFoundProduct() {
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubProductRepository productRepository = new StubProductRepository();
        ReduceOrderProductService service = new ReduceOrderProductService(orderProductRepository, productRepository);
        Product product = new Product("mate", "mate de cuero", 100, 5, "foto.jpg", 8);
        productRepository.save(product);
        OrderProduct orderProduct = new OrderProduct(1, 10, 5, product.getPrice());
        orderProductRepository.add(orderProduct);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(orderProduct.getId(), 1)
        );
        assertEquals("Product Not Found", exception.getMessage());

    }
    @Test
    void shouldThrowExceptionWhenNotFoundOrderProduct() {
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubProductRepository productRepository = new StubProductRepository();
        ReduceOrderProductService service = new ReduceOrderProductService(orderProductRepository, productRepository);
        Product product = new Product("mate", "mate de cuero", 100, 5, "foto.jpg", 8);
        productRepository.save(product);
        OrderProduct orderProduct = new OrderProduct(1, product.getId(), 5, product.getPrice());
        orderProductRepository.add(orderProduct);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(10, 1)
        );
        assertEquals("Order Product Not Found", exception.getMessage());

    }



}

