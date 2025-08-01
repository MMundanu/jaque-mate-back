package main.java.com.jaqueMate.domain.model;


public class Product {
    private static int NEXT_ID = 1;
    private final int id;
    private String name;
    private String description;
    private double price;
    private int category_id;
    private String image;
    private int stock;

    public Product(String name, String description, double price, int category_id, String image, int stock){
        this.id = NEXT_ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.image = image;
        this.stock = stock;
        NEXT_ID ++;
    }

    public Product(int id, String name, String description, double price, int category_id, String image, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.image = image;
        this.stock = stock;
    }




    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public double getPrice(){
        return this.price;
    }


    public int getCategoryId(){
        return this.category_id;
    }


    public String getImage(){
        return this.image;
    }


    public int getStock(){
        return this.stock;
    }



    public void setName(String name){
            this.name = name;

    }


    public void setDescription(String description){
        this.description = description;
    }


    public void setPrice(double price){
            this.price = price;
    }


    public void setCategoryId(int category_id){
        this.category_id = category_id;
    }

    public void setImage(String image){
        this.image = image;
    }


    public void setStock(int stock){
        if(stock >= 0){
            this.stock = stock;
        }
    }

}