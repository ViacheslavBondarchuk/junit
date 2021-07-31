package service.impl;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.DiscountService;
import service.PaymentService;
import util.DataLoaderUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PaymentServiceImplTest {
    private final PaymentService paymentService = new PaymentServiceImpl();
    private final DiscountService discountService = new DiscountServiceImpl();

    @Test
    public void testPriceItemsA() throws IOException {
        List<Item> itemsA = DataLoaderUtil.loadItems(ItemName.A);
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsA, ItemName.A, discountService.findDiscount(ItemName.A)))));
        Assertions.assertEquals(8.5, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(10, paymentInfo.getPrice());
    }

    @Test
    public void testPriceItemsB() throws IOException {
        List<Item> itemsB = DataLoaderUtil.loadItems(ItemName.B);
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsB, ItemName.B, discountService.findDiscount(ItemName.B)))));
        Assertions.assertEquals(34, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(34, paymentInfo.getPrice());
    }

    @Test
    public void testPriceItemsC() throws IOException {
        List<Item> itemsC = DataLoaderUtil.loadItems(ItemName.C);
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsC, ItemName.C, discountService.findDiscount(ItemName.C)))));
        Assertions.assertEquals(7.0, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(8.0, paymentInfo.getPrice());
    }

    @Test
    public void testPriceItemsD() throws IOException {
        List<Item> itemsD = DataLoaderUtil.loadItems(ItemName.D);
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsD, ItemName.D, discountService.findDiscount(ItemName.D)))));
        Assertions.assertEquals(6.0, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(6.0, paymentInfo.getPrice());
    }

    @Test
    public void testCommonItemsPrice() throws IOException {
        List<Item> itemsA = DataLoaderUtil.loadItems(ItemName.A);
        List<Item> itemsB = DataLoaderUtil.loadItems(ItemName.B);
        List<Item> itemsC = DataLoaderUtil.loadItems(ItemName.C);
        List<Item> itemsD = DataLoaderUtil.loadItems(ItemName.D);
        PaymentInfo paymentInfo = paymentService.payment(new Order(
                        Arrays.asList(
                                new OrderInfo(itemsA, ItemName.A, discountService.findDiscount(ItemName.A)),
                                new OrderInfo(itemsB, ItemName.B, discountService.findDiscount(ItemName.B)),
                                new OrderInfo(itemsC, ItemName.C, discountService.findDiscount(ItemName.C)),
                                new OrderInfo(itemsD, ItemName.D, discountService.findDiscount(ItemName.D))
                        )
                )
        );
        Assertions.assertEquals(55.5, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(58.0, paymentInfo.getPrice());
    }

}