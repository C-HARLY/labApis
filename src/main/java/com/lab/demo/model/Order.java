package com.lab.demo.model;

public class Order {
    private int id;
    private String client;
    private String product;
    private int quantity;
    private double total;
    private String status;

    public Order() {}

    public Order(int id, String client, String product, int quantity, double total, String status) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}