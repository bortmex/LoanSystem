package ru.javaproject.loansystem;

import ru.javaproject.loansystem.matcher.ModelMatcher;
import ru.javaproject.loansystem.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javaproject.loansystem.model.BaseEntity.START_SEQ;

public class ProductTestData {

    public static final ModelMatcher<Product> MATCHERPRODUCT = ModelMatcher.of(Product.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getPrice(), actual.getPrice())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                    )
    );

    public static final int PRODUCT1_ID = START_SEQ + 7;
    public static final int PRODUCT2_ID = START_SEQ + 8;
    public static final int PRODUCT3_ID = START_SEQ + 9;
    public static final int PRODUCT4_ID = START_SEQ + 10;
    public static final int PRODUCT5_ID = START_SEQ + 11;
    public static final int PRODUCT6_ID = START_SEQ + 12;
    public static final int PRODUCT7_ID = START_SEQ + 13;

    public static final Product PRODUCT1 = new Product(PRODUCT1_ID, "ferrari1", 10000000, "cars");
    public static final Product PRODUCT2 = new Product(PRODUCT2_ID, "носки", 9900, "носки обыкновенные");
    public static final Product PRODUCT3 = new Product(PRODUCT3_ID, "BMW", 1230000, "машина");
    public static final Product PRODUCT4 = new Product(PRODUCT4_ID, "BMW1234", 123, "мана");
    public static final Product PRODUCT5 = new Product(PRODUCT5_ID, "gtf45", 1000, "dtf54");
    public static final Product PRODUCT6 = new Product(PRODUCT6_ID, "ferrari4", 10000000, "cars");
    public static final Product PRODUCT7 = new Product(PRODUCT7_ID, "ferrari3", 10000000, "cars");

    public static final List<Product> PRODUCT_LIST = Arrays.asList(PRODUCT1, PRODUCT2, PRODUCT3, PRODUCT4, PRODUCT5, PRODUCT6, PRODUCT7);
    public static final List<Product> PRODUCT_LIST1 = Arrays.asList(PRODUCT1, PRODUCT2, PRODUCT3, PRODUCT4);

    public static Product getCreatedProd() {
        return new Product(null, "ferrari1234124", 100000, "cars555");
    }

    public static Product getUpdatedProd() {
        return new Product(PRODUCT2_ID, PRODUCT2.getName(), PRODUCT2.getPrice(), "Обновленное описание");
    }
}
