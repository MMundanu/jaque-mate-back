package main.java.com.jaqueMate.domain.model;

import com.jaqueMate.domain.model.OrderStatus;

public class Order {
    private static int NEXT_ID = 1;
    private final int id;
    private String orderDate;
    private int client_id;
    public OrderStatus order_status;

    public Order(String orderDate, int client_id, OrderStatus order_status) {
        this.id = NEXT_ID;
        this.orderDate = orderDate;
        this.client_id = client_id;
        this.order_status = order_status;
        NEXT_ID++;
    }
    public Order(int id, String orderDate, int client_id, OrderStatus order_status) {
        this.id = id;
        this.orderDate = orderDate;
        this.client_id = client_id;
        this.order_status = order_status;
    }


    public int getId() { return this.id;}

    public String getOrderDate() {
        return this.orderDate;
    }

    public int getClient_id() {
        return this.client_id;
    }

    public OrderStatus getOrder_status() {return this.order_status;}

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setOrder_status(OrderStatus order_status) {this.order_status = order_status;}

}
