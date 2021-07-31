package util;

import java.io.InputStream;
import java.text.MessageFormat;

public final class DataLoaderUtil {
    private final static ClassLoader CLASS_LOADER = DataLoaderUtil.class.getClassLoader();
    private final static String DISCOUNTS_PATH = "discounts.json";
    private final static String ITEMS_PATH = "items{0}.json";

    public static InputStream loadDiscounts(){
        return CLASS_LOADER.getResourceAsStream(DISCOUNTS_PATH);
    }

    public static InputStream loadItems(String suffix) {
        return CLASS_LOADER.getResourceAsStream(MessageFormat.format(ITEMS_PATH,suffix));
    }
}
