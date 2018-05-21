package ru.javaproject.loansystem.web;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javaproject.loansystem.UserTestData.PARTNER1;
import static ru.javaproject.loansystem.UserTestData.PARTNER1_ID;
import static ru.javaproject.loansystem.UserTestData.USER;
import static ru.javaproject.loansystem.model.BaseEntity.START_SEQ;


public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(7)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }

    @Test
    public void testGetPartnerList() throws Exception {
        mockMvc.perform(get("/partnerlist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("partnerlist"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/partnerlist.jsp"))
                .andExpect(model().attribute("partners", hasSize(3)))
                .andExpect(model().attribute("partners", hasItem(
                        allOf(
                                hasProperty("id", is(PARTNER1_ID)),
                                hasProperty("name", is(PARTNER1.getName()))
                        )
                )));
    }

    @Test
    public void testGetStartPageForPartner() throws Exception {
        mockMvc.perform(get("/showCreditAppListAndProductListForPartner"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("showCreditAppListAndProductListForPartner"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/showCreditAppListAndProductListForPartner.jsp"));
    }

    @Test
    public void testGetPartnerForRep() throws Exception {
        mockMvc.perform(get("/showAllPartnerForRepresentative"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("showAndCreatePartnerForRepresentative"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/showAndCreatePartnerForRepresentative.jsp"))
                .andExpect(model().attribute("partnersForRep", hasSize(3)))
                .andExpect(model().attribute("partnersForRep", hasItem(
                        allOf(
                                hasProperty("id", is(PARTNER1_ID)),
                                hasProperty("name", is(PARTNER1.getName()))
                        )
                )));
    }
}