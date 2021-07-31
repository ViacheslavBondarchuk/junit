package service;

import model.Discount;

public interface DiscountService {
    Discount findDiscount(String itemUuid);
}
