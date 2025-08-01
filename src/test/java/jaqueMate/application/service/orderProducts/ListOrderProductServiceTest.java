package jaqueMate.application.service.orderProducts;

import com.jaqueMate.application.service.orderProduct.ListOrderProductService;
import com.jaqueMate.application.service.orderProduct.ReduceOrderProductService;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.OrderStatus;
import jaqueMate.application.service.orders.StubOrderRepository;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.Order;
import main.java.com.jaqueMate.domain.model.OrderProduct;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;


public class ListOrderProductServiceTest {
    @Test
    public void listOrderProducts(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        ListOrderProductService service = new ListOrderProductService(orderProductRepository, orderRepository);
        Order order = new Order("dasda", 1, OrderStatus.CURRENT);
        orderRepository.save(order);
        OrderProduct orderProduct1 = new OrderProduct(order.getId(), 1, 1, 100);
        OrderProduct orderProduct2 = new OrderProduct(order.getId(), 2, 1, 100);
        OrderProduct orderProduct3 = new OrderProduct(order.getId(), 3, 1, 100);
        orderProductRepository.add(orderProduct1);
        orderProductRepository.add(orderProduct2);
        orderProductRepository.add(orderProduct3);


        List<OrderProduct> listOrderProduct = service.execute(order.getId());

        assertEquals(3, listOrderProduct.size());

        assertTrue(listOrderProduct.contains(orderProduct1));
        assertTrue(listOrderProduct.contains(orderProduct2));
        assertTrue(listOrderProduct.contains(orderProduct3));

        assertIterableEquals(List.of(orderProduct1, orderProduct2, orderProduct3), listOrderProduct);



    }
    @Test
    public void shouldThrowExceptionWhenOrderNotFound(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubOrderProductRepository orderProductRepository = new StubOrderProductRepository();
        ListOrderProductService service = new ListOrderProductService(orderProductRepository, orderRepository);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute('1')
        );
        assertEquals("Order Not Found", exception.getMessage());



    }

}
