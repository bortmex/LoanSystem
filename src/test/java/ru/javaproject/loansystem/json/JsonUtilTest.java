package ru.javaproject.loansystem.json;

import org.junit.Test;
import ru.javaproject.loansystem.CreditApplicationTestData;
import ru.javaproject.loansystem.ProductTestData;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.web.json.JsonUtil;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(CreditApplicationTestData.CREDIT_APPLICATION1);
        System.out.println(json);
        CreditApplication userMeal = JsonUtil.readValue(json, CreditApplication.class);
        CreditApplicationTestData.MATCHERCREDITAPPLICATION.assertEquals(CreditApplicationTestData.CREDIT_APPLICATION1, userMeal);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(ProductTestData.PRODUCT_LIST);
        System.out.println(json);
        List<Product> userMeals = JsonUtil.readValues(json, Product.class);
        ProductTestData.MATCHERPRODUCT.assertCollectionEquals(ProductTestData.PRODUCT_LIST, userMeals);
    }
}