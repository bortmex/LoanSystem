package ru.javaproject.loansystem.web;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.loansystem.UserTestData;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.repository.UserRepository;
import ru.javaproject.loansystem.util.exception.NotFoundException;
import ru.javaproject.loansystem.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Collection;

import static ru.javaproject.loansystem.UserTestData.ADMIN;
import static ru.javaproject.loansystem.UserTestData.USER;

public class InMemoryAdminRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;


    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/mock.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        /*
        appCtx.close();*/
    }

    @Before
    public void setUp() throws Exception {
        // Re-initialize
        UserRepository repository = appCtx.getBean(UserRepository.class);
        repository.getAll().forEach(u -> repository.delete(u.getId()));
        repository.save(USER);
        repository.save(ADMIN);
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }
}
