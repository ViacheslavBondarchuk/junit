package service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Discount;
import model.ItemName;
import service.DiscountService;
import util.DataLoaderUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscountServiceImpl implements DiscountService {
    private Map<ItemName, Discount> discountMap;

    public DiscountServiceImpl() {
        init();
    }

    private void init() {
        try (InputStream inputStream = DataLoaderUtil.loadDiscounts()) {
            discountMap = Optional.ofNullable(new ObjectMapper().readValue(new InputStreamReader(inputStream), new TypeReference<List<Discount>>() {}))
                    .map(discounts -> discounts.stream()
                            .collect(Collectors.toMap(k -> ItemName.valueOf(k.getItemUuid().toUpperCase()), v -> v))
                    ).orElseThrow(() -> new RuntimeException("Cannot init discounts map"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Discount findDiscount(ItemName itemName) {
        return discountMap.get(itemName);
    }
}
