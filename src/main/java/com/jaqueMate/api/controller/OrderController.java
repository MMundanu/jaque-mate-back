package com.jaqueMate.api.controller;


import com.jaqueMate.application.service.order.CreateOrderService;
import com.jaqueMate.application.service.order.UpdateStatusOrderService;
import com.jaqueMate.domain.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderService createOrderService;
    private final UpdateStatusOrderService updateStatusOrderService;

    @Autowired
    public OrderController(CreateOrderService createOrderService, UpdateStatusOrderService updateStatusOrderService) {
        this.createOrderService = createOrderService;
        this.updateStatusOrderService = updateStatusOrderService;
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody CreateOrderService.CreateOrderRequest request) {
        createOrderService.execute(request);
    }
    @PutMapping("/update/{id}")
    public void updateStateOrder(@PathVariable int id, @RequestBody OrderStatus orderStatus){
        updateStatusOrderService.execute(id, orderStatus);
    }
}


