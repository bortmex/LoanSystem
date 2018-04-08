package ru.javaproject.loansystem;

import ru.javaproject.loansystem.matcher.ModelMatcher;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;

import java.util.Arrays;
import java.util.List;

import static ru.javaproject.loansystem.CreditApplicationTestData.CREDIT_APPLICATION1_ID;
import static ru.javaproject.loansystem.CreditApplicationTestData.CREDIT_APPLICATION2_ID;
import static ru.javaproject.loansystem.ProductTestData.*;
import static ru.javaproject.loansystem.model.BaseEntity.START_SEQ;

public class CreditApplicationListProductTestData {

    public static final int CREDIT_APPLICATION_LIST_PRODUCT1_ID = START_SEQ + 17;
    public static final int CREDIT_APPLICATION_LIST_PRODUCT2_ID = START_SEQ + 18;
    public static final int CREDIT_APPLICATION_LIST_PRODUCT3_ID = START_SEQ + 19;
    public static final int CREDIT_APPLICATION_LIST_PRODUCT4_ID = START_SEQ + 20;

    public static final ModelMatcher<CreditApplicationListProduct> MATCHER_LIST_PRODUCT_MODEL_MATCHER = ModelMatcher.of(CreditApplicationListProduct.class);

    public static final CreditApplicationListProduct CREDIT_APPLICATION_LIST_PRODUCT1 = new CreditApplicationListProduct(CREDIT_APPLICATION_LIST_PRODUCT1_ID,
                                                                                                                         CREDIT_APPLICATION1_ID,PRODUCT1_ID);
    public static final CreditApplicationListProduct CREDIT_APPLICATION_LIST_PRODUCT2 = new CreditApplicationListProduct(CREDIT_APPLICATION_LIST_PRODUCT2_ID,
                                                                                                                         CREDIT_APPLICATION1_ID,PRODUCT2_ID);
    public static final CreditApplicationListProduct CREDIT_APPLICATION_LIST_PRODUCT3 = new CreditApplicationListProduct(CREDIT_APPLICATION_LIST_PRODUCT3_ID,
                                                                                                                         CREDIT_APPLICATION2_ID,PRODUCT5_ID);
    public static final CreditApplicationListProduct CREDIT_APPLICATION_LIST_PRODUCT4 = new CreditApplicationListProduct(null,
                                                                                                                         CREDIT_APPLICATION2_ID,PRODUCT6_ID);

    public static final List<CreditApplicationListProduct> CREDIT_APPLICATION_LIST_PRODUCTS = Arrays.asList(CREDIT_APPLICATION_LIST_PRODUCT1, CREDIT_APPLICATION_LIST_PRODUCT2);

    public static CreditApplicationListProduct getCreatedCALP() {
        return CREDIT_APPLICATION_LIST_PRODUCT4;
    }
}
