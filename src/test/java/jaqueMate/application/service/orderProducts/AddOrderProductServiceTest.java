package jaqueMate.application.service.orderProducts;

import com.jaqueMate.application.service.orderProduct.AddOrderProductService;
import com.jaqueMate.domain.exceptions.InsufficientStockException;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.OrderStatus;
import jaqueMate.application.service.orders.StubOrderRepository;
import jaqueMate.application.service.products.StubProductRepository;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.Product;
import main.java.com.jaqueMate.domain.model.Order;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import main.java.com.jaqueMate.domain.model.OrderProduct;


public class AddOrderProductServiceTest {
    @Test
    void addOrderProduct(){
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubProductRepository productRepository = new StubProductRepository();
        AddOrderProductService service = new AddOrderProductService(orderProductRepository, productRepository, orderRepository);
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 5);
        productRepository.save(product);
        Order order = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(order);
        AddOrderProductService.OrderProductRequest request = new AddOrderProductService.OrderProductRequest(product.getId(), order.getId(), 2);

        service.execute(request);

        Optional<OrderProduct> saved = orderProductRepository.getById(1);
        assertTrue(saved.isPresent());
        assertEquals(2, saved.get().getQuantity());
        assertEquals(3, product.getStock());

    }
    @Test
    void addQuantityInOrderProduct(){
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubProductRepository productRepository = new StubProductRepository();
        AddOrderProductService service = new AddOrderProductService(orderProductRepository, productRepository, orderRepository);
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 5);
        productRepository.save(product);
        Order order = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(order);
        OrderProduct orderProduct = new OrderProduct(order.getId(), product.getId(), 1, product.getPrice());
        orderProductRepository.add(orderProduct);
        AddOrderProductService.OrderProductRequest request = new AddOrderProductService.OrderProductRequest(product.getId(), order.getId(), 2);

        service.execute(request);

        Optional<OrderProduct> saved = orderProductRepository.getById(orderProduct.getId());
        assertTrue(saved.isPresent());
        assertEquals(3, saved.get().getQuantity());
        assertEquals(300, saved.get().getUnit_price());
        assertEquals(3, product.getStock());

    }
    @Test
    void shouldThrowExceptionWhenInsufficientStock(){
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubProductRepository productRepository = new StubProductRepository();
        AddOrderProductService service = new AddOrderProductService(orderProductRepository, productRepository, orderRepository);
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 5);
        productRepository.save(product);
        Order order = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(order);
        AddOrderProductService.OrderProductRequest request = new AddOrderProductService.OrderProductRequest(product.getId(), order.getId(), 6);

        InsufficientStockException exception = assertThrows(
                InsufficientStockException.class,
                () -> service.execute(request)
        );
        assertEquals("Insufficient Stock", exception.getMessage());

    }
    @Test
    void shouldThrowExceptionWhenOrderNotFound(){
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubProductRepository productRepository = new StubProductRepository();
        AddOrderProductService service = new AddOrderProductService(orderProductRepository, productRepository, orderRepository);
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 5);
        productRepository.save(product);
        Order order = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(order);
        AddOrderProductService.OrderProductRequest request = new AddOrderProductService.OrderProductRequest(product.getId(), 20, 2);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(request)
        );
        assertEquals("Order Not Found", exception.getMessage());

    }
    @Test
    void shouldThrowExceptionWhenProductNotFound(){
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubProductRepository productRepository = new StubProductRepository();
        AddOrderProductService service = new AddOrderProductService(orderProductRepository, productRepository, orderRepository);
        Product product = new Product("mate", "mate de cuero", 100, 1, "foto.jpg", 5);
        productRepository.save(product);
        Order order = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(order);
        AddOrderProductService.OrderProductRequest request = new AddOrderProductService.OrderProductRequest(20, order.getId(), 2);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(request)
        );
        assertEquals("Product Not Found", exception.getMessage());

    }



}
