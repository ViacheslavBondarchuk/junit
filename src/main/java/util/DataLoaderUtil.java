package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public final class DataLoaderUtil {
    private final static ClassLoader CLASS_LOADER = DataLoaderUtil.class.getClassLoader();
    private final static String DISCOUNTS_PATH = "discounts.json";
    private final static String ITEMS_PATH = "items{0}.json";

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static InputStream loadDiscounts() {
        return CLASS_LOADER.getResourceAsStream(DISCOUNTS_PATH);
    }

    @SuppressWarnings("unchecked")
    public static List<Item> loadItems(String suffix) throws IOException {
        return OBJECT_MAPPER.readValue(
                        new InputStreamReader(CLASS_LOADER.getResourceAsStream(MessageFormat.format(ITEMS_PATH, suffix))),
                        new TypeReference<List<Item>>() {}
                ).stream()
                .filter(item -> item.getPrice() >= 0.0)
                .collect(Collectors.toList());
    }
}
