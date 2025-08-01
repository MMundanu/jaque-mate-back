package main.java.com.jaqueMate.domain.port;


import main.java.com.jaqueMate.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    void cancel(int orderId);
    void finish(int orderId);
    Optional<Order> getOrderById(int id);
}
