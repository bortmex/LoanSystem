package ru.javaproject.loansystem.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.repository.JpaUtil;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javaproject.loansystem.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        userService.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }

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
        User user = userService.get(ADMIN_ID);
        MATCHERUSER.assertEquals(ADMIN, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFoundUser() throws Exception {
        userService.get(1);
    }

    @Test
    public void testGetByEmailUser() throws Exception {
        User user = userService.getByEmail("admin@gmail.com");
        MATCHERUSER.assertEquals(ADMIN, user);
    }

    @Test
    public void testGetAllUser() throws Exception {
        Collection<User> all = userService.getAll();
        MATCHERUSER.assertCollectionEquals(Arrays.asList(ADMIN, PARTNER1, PARTNER2, PARTNER3, REPRESENTATIVE, USER,USER1), all);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User updated = new User(USER);
        String oldName = updated.getName();
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        userService.update(updated);
        MATCHERUSER.assertEquals(updated, userService.get(USER_ID));
        updated.setName(oldName);
        userService.update(updated);
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> userService.save(new User(null, "  ", "invalid@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.save(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.save(new User(null, "User", "invalid@yandex.ru", "  ", Role.ROLE_USER)), ConstraintViolationException.class);
    }
}
