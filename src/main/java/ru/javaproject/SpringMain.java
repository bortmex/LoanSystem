package ru.javaproject;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.model.Questionnaire;
import ru.javaproject.web.quest.QuestRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            QuestRestController adminUserController = appCtx.getBean(QuestRestController.class);
            adminUserController.create(new Questionnaire(3, 1,"Ж",27,72300,  "raketa", 450000, 55, 1));
        }
    }
}
