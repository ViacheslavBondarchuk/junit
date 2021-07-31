package model;

import java.util.ArrayList;
import java.util.List;

public final class PaymentInfo {
    private float price;
    private float discountPrice;
    private List<OrderInfo> orderInfos;

    public PaymentInfo(float price, float discountPrice, List<OrderInfo> orderInfos) {
        this.price = price;
        this.discountPrice = discountPrice;
        this.orderInfos = orderInfos;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public List<OrderInfo> getOrderInfos() {
        return new ArrayList<>(orderInfos);
    }
}
