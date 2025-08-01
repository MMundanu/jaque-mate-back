package jaqueMate.application.service.orderProducts;
import main.java.com.jaqueMate.domain.port.OrderProductRepository;
import main.java.com.jaqueMate.domain.model.OrderProduct;

import java.util.*;
import java.util.stream.Collectors;

public class StubOrderProductRepository implements OrderProductRepository{
    private final Map<Integer, OrderProduct> orderProducts = new HashMap<>();

    @Override
    public void add(OrderProduct orderProduct) {
        orderProducts.put(orderProduct.getId(), orderProduct);
    }

    @Override
    public void update(OrderProduct orderProduct) {
        orderProducts.put(orderProduct.getId(), orderProduct);
    }

    @Override
    public void delete(int orderId) {
        orderProducts.remove(orderId);
    }

    @Override
    public Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId) {
        for (OrderProduct orderProduct : orderProducts.values()) {
            if (orderProduct.getOrder_id() == orderId && orderProduct.getProduct_id() == productId) {
                return Optional.of(orderProduct);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderProduct> getById(int orderId) {
        return Optional.ofNullable(orderProducts.get(orderId));
    }

    @Override
    public List<OrderProduct> getAllByOrderId(int orderId) {
        return orderProducts.values().stream()
                .filter(op -> op.getOrder_id() == orderId)
                .collect(Collectors.toList());

    }

}

