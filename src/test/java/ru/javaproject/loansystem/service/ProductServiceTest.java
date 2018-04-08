package ru.javaproject.loansystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

import static ru.javaproject.loansystem.ProductTestData.*;
import static ru.javaproject.loansystem.UserTestData.*;

public class ProductServiceTest extends AbstractServiceTest{

    @Autowired
    private ProductService productservice;

    @Test
    public void testDeleteProduct() throws Exception {
        productservice.delete(PRODUCT1_ID, PARTNER2_ID);
        MATCHERPRODUCT.assertCollectionEquals(Arrays.asList(PRODUCT2, PRODUCT3, PRODUCT4, PRODUCT5, PRODUCT6, PRODUCT7), productservice.getAll());
    }

    @Test
    public void testDeleteNotFoundProduct() throws Exception {
        thrown.expect(NotFoundException.class);
        productservice.delete(PRODUCT1_ID, 1);
    }

    @Test
    public void testSaveProduct() throws Exception {
        Product created = getCreatedProd();
        productservice.save(created, PARTNER2_ID);
        MATCHERPRODUCT.assertCollectionEquals(Arrays.asList(created, PRODUCT2, PRODUCT3, PRODUCT4), productservice.getAll(PARTNER2_ID));
    }

    @Test
    public void testGetProduct() throws Exception {
        Product actual = productservice.get(PRODUCT1_ID, PARTNER2_ID);
        MATCHERPRODUCT.assertEquals(PRODUCT1, actual);
    }

    @Test
    public void testGetNotFoundProduct() throws Exception {
        thrown.expect(NotFoundException.class);
        productservice.get(PRODUCT4_ID, PARTNER1_ID);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product updated = getUpdatedProd();
        productservice.update(updated, PARTNER2_ID);
        MATCHERPRODUCT.assertEquals(updated, productservice.get(PRODUCT2_ID, PARTNER2_ID));
    }

    @Test
    public void testUpdateNotFoundProduct() throws Exception {
        thrown.expect(NotFoundException.class);
        productservice.update(PRODUCT1, PARTNER3_ID);
    }

    @Test
    public void testGetAllProduct() throws Exception {
        MATCHERPRODUCT.assertCollectionEquals(PRODUCT_LIST1, productservice.getAll(PARTNER2_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> productservice.save(new Product(null, "  ", 12, "qwer"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> productservice.save(new Product(null, "Product", 21, " "), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> productservice.save(new Product(null, "Product", 10000001, "qwre"),USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> productservice.save(new Product(null, "Product", 9, "qwre"),USER_ID), ConstraintViolationException.class);
    }

}
