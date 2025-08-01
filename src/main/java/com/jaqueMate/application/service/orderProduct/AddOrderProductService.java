package com.jaqueMate.application.service.orderProduct;

import com.jaqueMate.domain.exceptions.InsufficientStockException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import main.java.com.jaqueMate.domain.port.OrderProductRepository;
import main.java.com.jaqueMate.domain.port.ProductRepository;
import main.java.com.jaqueMate.domain.model.OrderProduct;
import main.java.com.jaqueMate.domain.model.Order;
import main.java.com.jaqueMate.domain.model.Product;


import java.util.Optional;
import main.java.com.jaqueMate.domain.port.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddOrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AddOrderProductService(OrderProductRepository orderProductRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public void execute(OrderProductRequest request){
        Optional<Order> order = orderRepository.getOrderById(request.getOrderId());
        if(order.isEmpty()){
            throw new NotFoundException("Order Not Found");
        }

        Optional<Product> product = productRepository.getProductById(request.getProductId());

        if(product.isEmpty()){
            throw new NotFoundException("Product Not Found");
        }

        if(product.get().getStock() < request.getQuantity()){
            throw new InsufficientStockException("Insufficient Stock");
        }

        double unitPrice = product.get().getPrice() * request.getQuantity();

        productRepository.reduceStock(request.getProductId(), request.getQuantity());

        Optional<OrderProduct> oldOrderProduct = orderProductRepository.getOrderProductByOrderIdAndProductId(request.getOrderId(), request.getProductId());

        if(oldOrderProduct.isPresent()){
            oldOrderProduct.get().setQuantity(oldOrderProduct.get().getQuantity() + request.getQuantity());
            oldOrderProduct.get().setUnit_price(oldOrderProduct.get().getUnit_price() + unitPrice);
            orderProductRepository.update(oldOrderProduct.get());
        }

        OrderProduct newOrder = new OrderProduct(request.getOrderId(), request.getProductId(), request.getQuantity(), unitPrice);

        orderProductRepository.add(newOrder);





    }
    public static class OrderProductRequest{
        private final int productId;
        private final int orderId;
        private  final int quantity;

        public OrderProductRequest(int productId, int orderId, int quantity) {
            this.productId = productId;
            this.orderId = orderId;
            this.quantity = quantity;
        }

        public int getProductId() {return this.productId;}

        public int getOrderId() {return this.orderId;}

        public int getQuantity() {return this.quantity;}

    }

}
