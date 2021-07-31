package model;

import java.util.List;

public class OrderInfo {
    private int quantity;
    private String itemUuid;
    private float priceForPay;
    private float discountPriceForPay;

    public OrderInfo(List<Item> items, String itemUuid, Discount discount) {
        this.itemUuid = itemUuid;
        this.quantity = items.size();
        this.priceForPay = items.stream()
                .map(Item::getPrice)
                .reduce(0F, Float::sum);
        this.discountPriceForPay = calculateDiscountPrice(items, discount);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemUuid() {
        return itemUuid;
    }

    public float getPriceForPay() {
        return priceForPay;
    }

    public float getDiscountPriceForPay() {
        return discountPriceForPay;
    }

    private float calculateDiscountPrice(List<Item> items, Discount discount) {
        if (discount == null || items == null || items.size() == 0)
            return this.priceForPay;

        float priceForPay = 0F;
        int difference = quantity % discount.getQuantity();
        if (difference == 0) {
            priceForPay = ((float) (quantity / discount.getQuantity())) * discount.getPrice();
        } else {
            priceForPay = ((float) (quantity / discount.getQuantity())) * discount.getPrice() + ((float) difference * items.get(0).getPrice());
        }
        return priceForPay;
    }
}
