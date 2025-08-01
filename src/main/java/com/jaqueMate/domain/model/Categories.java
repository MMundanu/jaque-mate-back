package com.jaqueMate.domain.model;

public class Categories {
    private static int NEXT_ID = 1;
    private final int id;
    private String name;

    public Categories(String name) {
        this.id = NEXT_ID;
        this.name = name;
        NEXT_ID++;
    }

    public Categories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

}
