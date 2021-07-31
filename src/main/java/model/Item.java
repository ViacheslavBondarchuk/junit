package model;

public class Item {
    private String uuid;
    private float price;


    public Item() {}

    public Item(String uuid, float price) {
        this.uuid = uuid;
        this.price = price;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
