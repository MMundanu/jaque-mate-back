package com.jaqueMate.application.service.order;

import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.OrderStatus;
import main.java.com.jaqueMate.domain.port.OrderRepository;
import main.java.com.jaqueMate.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateStatusOrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public UpdateStatusOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(int order_id, OrderStatus orderStatus){
        Optional<Order> orderToUpdate = orderRepository.getOrderById(order_id);

        if(orderToUpdate.isEmpty()){
            throw new NotFoundException("Order not found");
        }

        if(orderStatus == OrderStatus.CANCELED){
            orderRepository.cancel(order_id);
        }

        if (orderStatus == OrderStatus.FINISHED){
            orderRepository.finish(order_id);
        }

    }
}
