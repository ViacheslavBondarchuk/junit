import model.*;
import service.DiscountService;
import service.PaymentService;
import service.impl.DiscountServiceImpl;
import service.impl.PaymentServiceImpl;
import util.DataLoaderUtil;

import java.io.IOException;
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
            List<Item> itemsA = DataLoaderUtil.<List<Item>>loadItems(ItemName.A);
            List<Item> itemsB = DataLoaderUtil.<List<Item>>loadItems(ItemName.B);
            List<Item> itemsC = DataLoaderUtil.<List<Item>>loadItems(ItemName.C);
            List<Item> itemsD = DataLoaderUtil.<List<Item>>loadItems(ItemName.D);
            PaymentInfo paymentInfo = paymentService.payment(new Order(
                            Arrays.asList(
                                    new OrderInfo(itemsA, ItemName.A, discountService.findDiscount(ItemName.A)),
                                    new OrderInfo(itemsB, ItemName.B, discountService.findDiscount(ItemName.B)),
                                    new OrderInfo(itemsC, ItemName.C, discountService.findDiscount(ItemName.C)),
                                    new OrderInfo(itemsD, ItemName.D, discountService.findDiscount(ItemName.D))
                            )
                    )
            );
        }

    }
}
