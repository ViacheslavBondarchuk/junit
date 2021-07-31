package model;

import java.io.Serializable;

public class Discount implements Serializable {
    private String itemUuid;
    private int quantity;
    private float price;

    public Discount() {
    }

    public Discount(String itemUuid, int quantity, float price) {
        this.itemUuid = itemUuid;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemUuid() {
        return itemUuid;
    }

    public void setItemUuid(String itemUuid) {
        this.itemUuid = itemUuid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
