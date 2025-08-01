package main.java.com.jaqueMate.domain.port;


import main.java.com.jaqueMate.domain.model.OrderProduct;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository {
    void add(OrderProduct orderProduct);
    void  update(OrderProduct orderProduct);
    void delete(int orderId);
    Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId);
    Optional<OrderProduct> getById(int orderId);
    List<OrderProduct> getAllByOrderId(int orderId);
}
