package com.jaqueMate.application.service.order;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.OrderStatus;
import com.jaqueMate.domain.port.UserRepository;
import main.java.com.jaqueMate.domain.port.OrderRepository;
import main.java.com.jaqueMate.domain.model.User;
import main.java.com.jaqueMate.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateOrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;


    @Autowired
    public CreateOrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void execute(CreateOrderRequest request){

        Optional<User> client = userRepository.getUserById(request.getClient_id());

        if (client.isEmpty()){
            throw new NotFoundException("Client not found");
        }

        if (request.getOrderDate() == null || request.getOrderDate().isBlank() ){
            throw new InvalidDataException("Order date is required");
        }

        Order newOrder = new Order(request.getOrderDate(), request.getClient_id(), OrderStatus.CURRENT);

        orderRepository.save(newOrder);


    }

    public static class CreateOrderRequest{
        private final String orderDate;
        private final int client_id;

        public CreateOrderRequest(String orderDate, int client_id) {
            this.orderDate = orderDate;
            this.client_id = client_id;

        }


        public String getOrderDate() {
            return this.orderDate;
        }

        public int getClient_id() {
            return this.client_id;
        }

    }

}
