package ru.javaproject.loansystem.web.creditapp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javaproject.loansystem.TestUtil;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.service.CreditApplicationService;
import ru.javaproject.loansystem.web.AbstractControllerTest;
import ru.javaproject.loansystem.web.creditapplication.CreditApplicationRestController;
import ru.javaproject.loansystem.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaproject.loansystem.CreditApplicationTestData.*;
import static ru.javaproject.loansystem.UserTestData.PARTNER2_ID;

public class CreditApplicationRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = CreditApplicationRestController.REST_URL + '/';

    @Autowired
    private CreditApplicationService service;

    @Test
    public void testGetAllForUser() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "user/my"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHERCREDITAPPLICATION.contentListMatcher(CREDIT_APPLICATION1, CREDIT_APPLICATION2)));
    }


    @Test
    public void testGetForUser() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + CREDIT_APPLICATION1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHERCREDITAPPLICATION.contentMatcher(CREDIT_APPLICATION1)));
    }

    @Test
    public void testGetForPartner() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "partner/showCreditAppListForPartner" + "/" + PARTNER2_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHERCREDITAPPLICATION.contentListMatcher(CREDIT_APPLICATION1)));
    }

    @Test
    public void testGetForResentative() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "resentative/showcreditapplistforrepresentative"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHERCREDITAPPLICATION.contentListMatcher(CREDIT_APPLICATION1,CREDIT_APPLICATION2,CREDIT_APPLICATION3)));
    }


    @Test
    public void testCreateCredApp() throws Exception {
        CreditApplication created = getCreatedCA();
        ResultActions action = mockMvc.perform(post(REST_URL + "creditApplication/add/" + PARTNER2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        CreditApplication returned = MATCHERCREDITAPPLICATION.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHERCREDITAPPLICATION.assertEquals(created, returned);
        MATCHERCREDITAPPLICATION.assertCollectionEquals(Arrays.asList(CREDIT_APPLICATION1, CREDIT_APPLICATION2, CREDIT_APPLICATION3, created), service.getAll());
    }



    @Test
    public void testCreateCreditApplication() throws Exception {
        CreditApplication created = new CreditApplication("", null, LocalDateTime.now(), "", null);
        ResultActions action = mockMvc.perform(get(REST_URL + "user/create/" + PARTNER2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        CreditApplication returned = MATCHERCREDITAPPLICATION.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHERCREDITAPPLICATION.assertEquals(created, returned);
    }
}
