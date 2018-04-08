package ru.javaproject.loansystem.web.product;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javaproject.loansystem.TestUtil;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.service.ProductService;
import ru.javaproject.loansystem.web.AbstractControllerTest;
import ru.javaproject.loansystem.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaproject.loansystem.ProductTestData.*;
import static ru.javaproject.loansystem.UserTestData.PARTNER2_ID;

public class ProductRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProductRestController.REST_URL + '/';

    @Autowired
    private ProductService service;

    @Test
    public void testGetProd() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + PRODUCT1_ID + '/' + PARTNER2_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHERPRODUCT.contentMatcher(PRODUCT1)));
    }

    @Test
    public void testCreateProd() throws Exception {
        Product created = getCreatedProd();
        ResultActions action = mockMvc.perform(post(REST_URL + "partner/add/" + PARTNER2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Product returned = MATCHERPRODUCT.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHERPRODUCT.assertEquals(created, returned);
        MATCHERPRODUCT.assertCollectionEquals(Arrays.asList(PRODUCT1, PRODUCT2, PRODUCT3,PRODUCT4,PRODUCT5,PRODUCT6, PRODUCT7, created), service.getAll());
    }

    @Test
    public void testCreateProductNull() throws Exception {
        Product created = new Product("", 0, "");
        ResultActions action = mockMvc.perform(get(REST_URL + "partner/create/" + PARTNER2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Product returned = MATCHERPRODUCT.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHERPRODUCT.assertEquals(created, returned);
    }

    @Test
    public void testGetAllProductForPartner() throws Exception {
        mockMvc.perform(get(REST_URL + "user/see/" + PARTNER2_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHERPRODUCT.contentListMatcher(PRODUCT1, PRODUCT2, PRODUCT3, PRODUCT4));
    }
}