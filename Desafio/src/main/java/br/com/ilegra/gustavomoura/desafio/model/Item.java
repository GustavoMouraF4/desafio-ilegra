package br.com.ilegra.gustavomoura.desafio.model;

public class Item {
    private Integer itemId;
    private Integer itemQuantity;
    private Double itemPrice;

    public Item(Integer itemId, Integer itemQuantity, Double itemPrice) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public Double getItemPrice() {
        return itemPrice;
    }
}
