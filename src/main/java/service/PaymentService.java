package service;

import model.Order;
import model.PaymentInfo;

public interface PaymentService {
    PaymentInfo payment(Order order);
}
