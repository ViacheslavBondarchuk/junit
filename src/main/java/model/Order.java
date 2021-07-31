package model;

import java.util.ArrayList;
import java.util.List;

public final class Order {
    private List<OrderInfo> orderInfos;

    public Order(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public List<OrderInfo> getOrderInfos() {
        return new ArrayList<>(orderInfos);
    }
}
