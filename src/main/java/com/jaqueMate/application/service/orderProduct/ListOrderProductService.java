package com.jaqueMate.application.service.orderProduct;

import com.jaqueMate.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListOrderProductService {
    private final main.java.com.jaqueMate.domain.port.OrderProductRepository orderProductRepository;
    private final main.java.com.jaqueMate.domain.port.OrderRepository orderRepository;

    @Autowired
    public ListOrderProductService(main.java.com.jaqueMate.domain.port.OrderProductRepository orderProductRepository, main.java.com.jaqueMate.domain.port.OrderRepository orderRepository) {
        this.orderProductRepository = orderProductRepository;
        this.orderRepository = orderRepository;
    }
    public List<main.java.com.jaqueMate.domain.model.OrderProduct> execute(int orderId){
        Optional<main.java.com.jaqueMate.domain.model.Order> order = orderRepository.getOrderById(orderId);

        if(order.isEmpty()){
            throw new NotFoundException("Order Not Found");
        }

        return orderProductRepository.getAllByOrderId(orderId);

    }
}