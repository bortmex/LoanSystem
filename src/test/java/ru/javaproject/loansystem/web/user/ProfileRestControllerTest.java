package ru.javaproject.loansystem.web.user;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javaproject.loansystem.TestUtil;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.web.AbstractControllerTest;
import ru.javaproject.loansystem.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaproject.loansystem.UserTestData.*;
import static ru.javaproject.loansystem.web.user.ProfileRestController.REST_URL;

public class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andExpect(status().isOk());
        assertMatch(userService.getAll(), ADMIN, PARTNER1, PARTNER2, PARTNER3, REPRESENTATIVE, USER1);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER_ID, "newName", "newemail@ya.ru", "newPassword", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHERUSER.assertEquals(updated, new User(userService.getByEmail("newemail@ya.ru")));
    }

    @Test
    public void testCreatePartner() throws Exception {
        ResultActions action = mockMvc.perform(post(REST_URL + "/partner/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(PARTNER_NEW)));

        User returned = MATCHERUSER.fromJsonAction(action);
        PARTNER_NEW.setId(returned.getId());

        MATCHERUSER.assertEquals(PARTNER_NEW, returned);
    }
}