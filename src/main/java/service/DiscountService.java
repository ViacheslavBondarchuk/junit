package service;

import model.Discount;
import model.ItemName;

public interface DiscountService {
    Discount findDiscount(ItemName itemName);
}
