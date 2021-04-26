package br.com.ilegra.gustavomoura.desafio.model;

import java.util.ArrayList;

public class Sale {
    private Integer saleId;
    private ArrayList<Item> items;
    private String salesmanName;
    private double totalPrice;

    public Sale(Integer saleId, ArrayList<Item> items, String salesmanName) {
        this.saleId = saleId;
        this.items = items;
        this.salesmanName = salesmanName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
