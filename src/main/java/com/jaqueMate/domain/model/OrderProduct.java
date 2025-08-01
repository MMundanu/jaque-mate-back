package main.java.com.jaqueMate.domain.model;

public class OrderProduct {
    private static int NEXT_ID = 1;
    private final int id;
    public int order_id;
    public int product_id;
    public int quantity;
    public double unit_price;


    public OrderProduct(int order_id, int product_id, int quantity, double unit_price) {
        this.id = NEXT_ID;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
        NEXT_ID++;

    }
    public OrderProduct(int id, int order_id, int product_id, int quantity, double unit_price) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;

    }

    public int getId() { return this.id;}

    public int getOrder_id() {return this.order_id;}

    public int getProduct_id() {return this.product_id;}

    public int getQuantity() {return this.quantity;}

    public double getUnit_price() {return this.unit_price;}


    public void setOrder_id(int order_id) {this.order_id = order_id;}

    public void setProduct_id(int product_id) {this.product_id = product_id;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void setUnit_price(double unit_price) {this.unit_price = unit_price;}


}
