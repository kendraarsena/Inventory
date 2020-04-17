package com.example.uts_mcs;

public class Product {
    public String name, desc;
    public int qty, id;

    public Product(String name, String desc, int qty, int id) {
        this.name = name;
        this.desc = desc;
        this.qty = qty;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
