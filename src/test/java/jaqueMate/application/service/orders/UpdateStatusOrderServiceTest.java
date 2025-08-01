package jaqueMate.application.service.orders;
import com.jaqueMate.application.service.order.UpdateStatusOrderService;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.OrderStatus;
import main.java.com.jaqueMate.domain.model.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



public class UpdateStatusOrderServiceTest {
    @Test
    public void updateStatusCanceledOrder(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        Order oldOrder = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(oldOrder);
        UpdateStatusOrderService service = new UpdateStatusOrderService(orderRepository);

        service.execute(oldOrder.getId(), OrderStatus.CANCELED);

        Optional<Order> updateOrder = orderRepository.getOrderById(oldOrder.getId());
        assertTrue(updateOrder.isPresent());
        assertEquals(OrderStatus.CANCELED,updateOrder.get().getOrder_status());
    }
    @Test
    public void updateStatusFinishedOrder(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        Order oldOrder = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(oldOrder);
        UpdateStatusOrderService service = new UpdateStatusOrderService(orderRepository);

        service.execute(oldOrder.getId(), OrderStatus.FINISHED);

        Optional<Order> updateOrder = orderRepository.getOrderById(oldOrder.getId());
        assertTrue(updateOrder.isPresent());
        assertEquals(OrderStatus.FINISHED,updateOrder.get().getOrder_status());
    }
    @Test
    public void shouldThrowExceptionWhenNotFoundOrder(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        Order oldOrder = new Order("63267", 1, OrderStatus.CURRENT);
        orderRepository.save(oldOrder);
        UpdateStatusOrderService service = new UpdateStatusOrderService(orderRepository);


        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(5000, OrderStatus.FINISHED)
        );
        assertEquals("Order not found", exception.getMessage());

    }


}
