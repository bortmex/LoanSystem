package ru.javaproject.loansystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;

import java.util.Arrays;

import static ru.javaproject.loansystem.CreditApplicationListProductTestData.*;
import static ru.javaproject.loansystem.CreditApplicationTestData.CREDIT_APPLICATION1_ID;

public class CreditApplicationListProductServiceTest extends AbstractServiceTest{

    @Autowired
    private CreditApplicationListProductService creditApplicationListProductService;

    @Test
    public void testDeleteWithCreditAppId() throws Exception {
        creditApplicationListProductService.delete(CREDIT_APPLICATION1_ID);
        MATCHER_LIST_PRODUCT_MODEL_MATCHER.assertCollectionEquals(Arrays.asList(CREDIT_APPLICATION_LIST_PRODUCT3), creditApplicationListProductService.getAll());
    }

    @Test
    public void testSave() throws Exception {
        CreditApplicationListProduct created = getCreatedCALP();
        CreditApplicationListProduct creditApplicationListProduct = creditApplicationListProductService.save(created);
        created.setId(creditApplicationListProduct.getId());
        MATCHER_LIST_PRODUCT_MODEL_MATCHER.assertCollectionEquals(Arrays.asList(CREDIT_APPLICATION_LIST_PRODUCT1, CREDIT_APPLICATION_LIST_PRODUCT2, CREDIT_APPLICATION_LIST_PRODUCT3, created), creditApplicationListProductService.getAll());
    }

    @Test
    public void testGet() throws Exception {
        CreditApplicationListProduct actual = creditApplicationListProductService.get(CREDIT_APPLICATION_LIST_PRODUCT1_ID);
        MATCHER_LIST_PRODUCT_MODEL_MATCHER.assertEquals(CREDIT_APPLICATION_LIST_PRODUCT1, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER_LIST_PRODUCT_MODEL_MATCHER.assertCollectionEquals(Arrays.asList(CREDIT_APPLICATION_LIST_PRODUCT1, CREDIT_APPLICATION_LIST_PRODUCT2, CREDIT_APPLICATION_LIST_PRODUCT3), creditApplicationListProductService.getAll());
    }

}
