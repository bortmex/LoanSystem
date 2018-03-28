package ru.javaproject.loansystem.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javaproject.loansystem.model.*;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static ru.javaproject.loansystem.CreditApplicationListProductTestData.*;
import static ru.javaproject.loansystem.CreditApplicationTestData.*;
import static ru.javaproject.loansystem.ProductTestData.*;
import static ru.javaproject.loansystem.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AllServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(AllServiceTest.class);
    private static StringBuilder results = new StringBuilder();


    @Autowired
    private CreditApplicationListProductService creditApplicationListProductService;

    @Autowired
    private CreditApplicationService credappservice;

    @Autowired
    private ProductService productservice;

    @Autowired
    private UserService userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {

        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            LOG.info(result + " ms\n");

        }
    };

    @Before
    public void setUserService() throws Exception {
        userService.evictCache();
    }


    @AfterClass
    public static void printResult() {
        LOG.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------\n" +
                results +
                "---------------------------------\n");
    }

//----------------------------------------------------------CreditApplicationListProduct--------------------------------------------------------

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

//----------------------------------------------------------CreditApplication--------------------------------------------------------

    @Test
    public void testDeleteCreditApplication() throws Exception {
        credappservice.delete(CREDIT_APPLICATION1_ID, USER_ID);
        MATCHERCREDITAPPLICATION.assertCollectionEquals(Arrays.asList(CREDIT_APPLICATION2), credappservice.getAllForUsersId(USER_ID));
    }

    @Test
    public void testDeleteNotFoundCreditApplication() throws Exception {
        thrown.expect(NotFoundException.class);
        credappservice.delete(CREDIT_APPLICATION1_ID, 1);
    }

    @Test
    public void testSaveCreditApplication() throws Exception {
        CreditApplication created = getCreatedCA();
        credappservice.save(created, USER_ID);
        MATCHERCREDITAPPLICATION.assertCollectionEquals(Arrays.asList(created, CREDIT_APPLICATION2), credappservice.getAllForUsersId(USER_ID));
    }

    @Test
    public void testGetCreditApplication() throws Exception {
        CreditApplication actual = credappservice.get(CREDIT_APPLICATION1_ID, USER_ID);
        MATCHERCREDITAPPLICATION.assertEquals(CREDIT_APPLICATION1, actual);
    }

    @Test
    public void testGetNotFoundCreditApplication() throws Exception {
        thrown.expect(NotFoundException.class);
        credappservice.get(CREDIT_APPLICATION1_ID, USER1_ID);
    }

    @Test
    public void testUpdateCreditApplication() throws Exception {
        CreditApplication updated = getUpdatedCA();
        credappservice.update(updated, USER_ID);
        MATCHERCREDITAPPLICATION.assertEquals(updated, credappservice.get(CREDIT_APPLICATION2_ID, USER_ID));
    }

    @Test
    public void testUpdateNotFoundCreditApplication() throws Exception {
        thrown.expect(NotFoundException.class);
        credappservice.update(CREDIT_APPLICATION3, USER_ID);
    }

    @Test
    public void testGetAllCreditApplication() throws Exception {
        MATCHERCREDITAPPLICATION.assertCollectionEquals(CREDIT_APPLICATION_LIST_USER, credappservice.getAllForUsersId(USER_ID));
    }
//----------------------------------------------------------Product--------------------------------------------------------

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
//----------------------------------------------------------User--------------------------------------------------------

    @Test
    public void testSaveUser() throws Exception {
        User created = userService.save(USER_NEW);
        USER_NEW.setId(created.getId());
        MATCHERUSER.assertCollectionEquals(Arrays.asList(ADMIN, USER_NEW, PARTNER1, PARTNER2, PARTNER3, REPRESENTATIVE, USER, USER1), userService.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSaveUser() throws Exception {
        userService.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void testDeleteUser() throws Exception {
        userService.delete(USER1_ID);
        MATCHERUSER.assertCollectionEquals(Arrays.asList(ADMIN, PARTNER1, PARTNER2, PARTNER3, REPRESENTATIVE, USER), userService.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDeleteUser() throws Exception {
        userService.delete(1);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = userService.get(USER_ID);
        MATCHERUSER.assertEquals(USER, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFoundUser() throws Exception {
        userService.get(1);
    }

    @Test
    public void testGetByEmailUser() throws Exception {
        User user = userService.getByEmail("user@yandex.ru");
        MATCHERUSER.assertEquals(USER, user);
    }

    @Test
    public void testGetAllUser() throws Exception {
        Collection<User> all = userService.getAll();
        MATCHERUSER.assertCollectionEquals(Arrays.asList(ADMIN, PARTNER1, PARTNER2, PARTNER3, REPRESENTATIVE, USER,USER1), all);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        userService.update(updated);
        MATCHERUSER.assertEquals(updated, userService.get(USER_ID));
    }

}
