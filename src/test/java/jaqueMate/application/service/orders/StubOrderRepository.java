package jaqueMate.application.service.orders;

import com.jaqueMate.domain.model.OrderStatus;
import main.java.com.jaqueMate.domain.port.OrderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import main.java.com.jaqueMate.domain.model.Order;

public class StubOrderRepository implements OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public void cancel(int order_id) {
        Order order = orders.get(order_id);
        order.setOrder_status(OrderStatus.CANCELED);
        orders.put(order.getId(), order);
    }

    @Override
    public void finish(int order_id) {
        Order order = orders.get(order_id);
        order.setOrder_status(OrderStatus.FINISHED);
        orders.put(order.getId(), order);
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.ofNullable(orders.get(id));
    }

}
