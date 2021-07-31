import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Item;
import model.Order;
import model.OrderInfo;
import model.PaymentInfo;
import service.DiscountService;
import service.PaymentService;
import service.impl.DiscountServiceImpl;
import service.impl.PaymentServiceImpl;
import util.DataLoaderUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        new Emulate(new DiscountServiceImpl(), new PaymentServiceImpl()).start();
    }

    static private class Emulate {
        private final DiscountService discountService;
        private final PaymentService paymentService;

        public Emulate(DiscountService discountService, PaymentService paymentService) {
            this.discountService = discountService;
            this.paymentService = paymentService;
        }

        public void start() throws IOException {
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
        }

    }
}
