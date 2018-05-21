package ru.javaproject.loansystem;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.web.product.AbstractProductController;
import ru.javaproject.loansystem.web.product.ProductController;
import ru.javaproject.loansystem.web.user.AdminRestController;

import java.util.Arrays;
import java.util.List;


public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/mock.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);

            List<User> users = adminUserController.getAll();
            System.out.println(users);

            AbstractProductController productRestController = appCtx.getBean(ProductController.class);
            productRestController.create(new Product( "ferrari1", 100000, "cars"), 100004);
            System.out.println();
        }
    }
}
