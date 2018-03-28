package ru.javaproject.loansystem;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.web.product.ProductRestController;

import java.util.Arrays;


public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/mock.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            ProductRestController productRestController = appCtx.getBean(ProductRestController.class);
            productRestController.create(new Product( "ferrari1", 100000, "cars"));
            System.out.println();
        }
    }
}
