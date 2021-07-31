package service.impl;

import model.Order;
import model.OrderInfo;
import model.PaymentInfo;
import service.PaymentService;

import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentInfo payment(Order order) {
        return Optional.ofNullable(order)
                .map(Order::getOrderInfos)
                .map(orderInfos -> new PaymentInfo(
                        orderInfos.stream()
                                .map(OrderInfo::getPriceForPay)
                                .reduce(0F, Float::sum),
                        orderInfos.stream()
                                .map(OrderInfo::getDiscountPriceForPay)
                                .reduce(0F, Float::sum),
                        orderInfos)
                ).orElseThrow(() -> new RuntimeException("Order is empty"));
    }
}
