package service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Item;
import model.Order;
import model.OrderInfo;
import model.PaymentInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.DiscountService;
import service.PaymentService;
import util.DataLoaderUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PaymentServiceImplTest {
    private final PaymentService paymentService = new PaymentServiceImpl();
    private final DiscountService discountService = new DiscountServiceImpl();

    @Test
    public void testPriceItemsA() throws IOException {
        List<Item> itemsA = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("A")), new TypeReference<List<Item>>() {});
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsA, "A", discountService.findDiscount("A")))));
        Assertions.assertEquals(8.5, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(10, paymentInfo.getPrice());
    }

    @Test
    public void testPriceItemsB() throws IOException {
        List<Item> itemsB = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("B")), new TypeReference<List<Item>>() {});
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsB, "B", discountService.findDiscount("B")))));
        Assertions.assertEquals(34, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(34, paymentInfo.getPrice());
    }

    @Test
    public void testPriceItemsC() throws IOException {
        List<Item> itemsC = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("C")), new TypeReference<List<Item>>() {});
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsC, "C", discountService.findDiscount("C")))));
        Assertions.assertEquals(7.0, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(8.0, paymentInfo.getPrice());
    }

    @Test
    public void testPriceItemsD() throws IOException {
        List<Item> itemsD = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("D")), new TypeReference<List<Item>>() {});
        PaymentInfo paymentInfo = paymentService.payment(new Order(Collections.singletonList(new OrderInfo(itemsD, "D", discountService.findDiscount("D")))));
        Assertions.assertEquals(6.0, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(6.0, paymentInfo.getPrice());
    }

    @Test
    public void testCommonItemsPrice() throws IOException {
        List<Item> itemsA = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("A")), new TypeReference<List<Item>>() {});
        List<Item> itemsB = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("B")), new TypeReference<List<Item>>() {});
        List<Item> itemsC = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("C")), new TypeReference<List<Item>>() {});
        List<Item> itemsD = new ObjectMapper().readValue(new InputStreamReader(DataLoaderUtil.loadItems("D")), new TypeReference<List<Item>>() {});
        PaymentInfo paymentInfo = paymentService.payment(new Order(
                        Arrays.asList(
                                new OrderInfo(itemsA, "A", discountService.findDiscount("A")),
                                new OrderInfo(itemsB, "B", discountService.findDiscount("B")),
                                new OrderInfo(itemsC, "C", discountService.findDiscount("C")),
                                new OrderInfo(itemsD, "D", discountService.findDiscount("D"))
                        )
                )
        );
        Assertions.assertEquals(55.5, paymentInfo.getDiscountPrice());
        Assertions.assertEquals(58.0, paymentInfo.getPrice());
    }

}