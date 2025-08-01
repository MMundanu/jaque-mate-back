package com.jaqueMate.application.service.orderProduct;
import com.jaqueMate.domain.exceptions.NotFoundException;
import main.java.com.jaqueMate.domain.port.OrderProductRepository;
import main.java.com.jaqueMate.domain.model.OrderProduct;

import java.util.List;
import java.util.Optional;
import main.java.com.jaqueMate.domain.port.ProductRepository;
import main.java.com.jaqueMate.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReduceOrderProductService {
    private OrderProductRepository orderProductRepository;
    private ProductRepository productRepository;

    @Autowired
    public ReduceOrderProductService(OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    public void execute(int orderProductId, int quantityToReduce){
        Optional<OrderProduct> orderProduct = orderProductRepository.getById(orderProductId);
        if(orderProduct.isEmpty()){
            throw new NotFoundException("Order Product Not Found");
        }

        Optional<Product> product = productRepository.getProductById(orderProduct.get().getProduct_id());

        if(product.isEmpty()){
            throw new NotFoundException("Product Not Found");
        }


        if(orderProduct.get().getQuantity() <=  quantityToReduce){
            int newStock = product.get().getStock() + orderProduct.get().getQuantity();
            product.get().setStock(newStock);
            productRepository.update(product.get());
            orderProductRepository.delete(orderProductId);
        }else {
            int newStock = product.get().getStock() + quantityToReduce;
            product.get().setStock(newStock);
            productRepository.update(product.get());
            orderProduct.get().setQuantity(orderProduct.get().getQuantity() - quantityToReduce);
            orderProduct.get().setUnit_price(orderProduct.get().getUnit_price() * orderProduct.get().getQuantity());
            orderProductRepository.update(orderProduct.get());
        }


    }

}
