package com.jaqueMate.api.controller;


import com.jaqueMate.application.service.orderProduct.AddOrderProductService;
import com.jaqueMate.application.service.orderProduct.ListOrderProductService;
import com.jaqueMate.application.service.orderProduct.ReduceOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import main.java.com.jaqueMate.domain.model.OrderProduct;

import java.util.List;

@RestController
@RequestMapping("/orderProduct")
public class OrderProductController {
    private final AddOrderProductService addOrderProductService;
    private final ListOrderProductService listOrderProductService;
    private final ReduceOrderProductService reduceOrderProductService;

    @Autowired
    public OrderProductController(AddOrderProductService addOrderProductService, ListOrderProductService listOrderProductService, ReduceOrderProductService reduceOrderProductService) {
        this.addOrderProductService = addOrderProductService;
        this.listOrderProductService = listOrderProductService;
        this.reduceOrderProductService = reduceOrderProductService;
    }
    @PostMapping("/add")
    public void addOrderProduct(@RequestBody AddOrderProductService.OrderProductRequest request){
        addOrderProductService.execute(request);
    }
    @GetMapping("/{orderId}")
    public List<OrderProduct> listOrderProduct(@PathVariable int orderId){
        return listOrderProductService.execute(orderId);
    }
    @PutMapping("/{order}")
    public void updateOrderProduct(@PathVariable int orderId, @RequestBody int quantity){
        reduceOrderProductService.execute(orderId, quantity);
    }
}
