package test.java.jaqueMate.application.service.orders;

import com.jaqueMate.application.service.order.CreateOrderService;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.OrderStatus;
import com.jaqueMate.domain.model.UserRole;
import jaqueMate.application.service.orders.StubOrderRepository;
import jaqueMate.application.service.users.StubUserRepository;
import org.junit.jupiter.api.Test;
import main.java.com.jaqueMate.domain.model.User;
import java.util.Optional;
import main.java.com.jaqueMate.domain.model.Order;
import static org.junit.jupiter.api.Assertions.*;



public class CreateOrderServiceTest {
    @Test
    public void createOrder(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubUserRepository userRepository = new StubUserRepository();
        User userExist = new User("mauro", "mauro@mail.com", "123456", UserRole.USER);
        userRepository.save(userExist);
        CreateOrderService service = new CreateOrderService(orderRepository,userRepository);
        CreateOrderService.CreateOrderRequest request = new CreateOrderService.CreateOrderRequest("278239", userExist.getId());

        service.execute(request);

        Optional<Order> saved = orderRepository.getOrderById(1);
        assertTrue(saved.isPresent());
        assertEquals(request.getOrderDate(),saved.get().getOrderDate());
        assertEquals(request.getClient_id(),saved.get().getClient_id());
        assertEquals(OrderStatus.CURRENT, saved.get().getOrder_status());

    }
    @Test
    public void shouldThrowExceptionWhenNotExistClientId(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubUserRepository userRepository = new StubUserRepository();
        User userExist = new User("mauro", "mauro@mail.com", "123456", UserRole.USER);
        userRepository.save(userExist);
        CreateOrderService service = new CreateOrderService(orderRepository,userRepository);
        CreateOrderService.CreateOrderRequest request = new CreateOrderService.CreateOrderRequest("278239", 10);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.execute(request)
        );
        assertEquals("Client not found", exception.getMessage());

    }
    @Test
    public void shouldThrowExceptionWhenOrderDateIsBlank(){
        StubOrderRepository orderRepository = new StubOrderRepository();
        StubUserRepository userRepository = new StubUserRepository();
        User userExist = new User("mauro", "mauro@mail.com", "123456", UserRole.USER);
        userRepository.save(userExist);
        CreateOrderService service = new CreateOrderService(orderRepository,userRepository);
        CreateOrderService.CreateOrderRequest request = new CreateOrderService.CreateOrderRequest("  ", userExist.getId());

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute(request)
        );
        assertEquals("Order date is required", exception.getMessage());

    }


}
