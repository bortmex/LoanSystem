package ru.javaproject.util;

import ru.javaproject.model.Questionnaire;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestsUtil {
    public static final List<Questionnaire> QUESTIONNAIRES = Arrays.asList(
            new Questionnaire(1,1,"М",24,35000, "Volga", 45000, 60 , 1),
            new Questionnaire(2,1,"Ж",56,56000,"UFO", 45,30, 2),
            new Questionnaire(3, 2,"М",27,78000,  "raketa", 450000, 55, 1)
    );

    public static List<Questionnaire> getQuestionnairesForRepresentative(List<Questionnaire> list){
        return list.stream().filter(quest -> quest.getDecisionRepresentative() == 1).collect(Collectors.toList());
    }

    public static String getStatisForClientAnket(Questionnaire questionnaire){
        int decisionR = questionnaire.getDecisionRepresentative();
        int decisionP = questionnaire.getDecisionPartner();
        if(decisionR!=0 && decisionR!=1 &&decisionP!=0){
            if(decisionP == 2 && decisionR==2){
                return "Yes";
            } else return "No";
        }
        return "Wait";
    }

    public static String getAnswerCreditScrollingSystem(Questionnaire questionnaire){
        /*this.floor = floor;
        this.age = age;
        this.income = income;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.credit_term_day = credit_term_day;*/

        if(questionnaire.getFloor().equals("М") && questionnaire.getAge()>18 && questionnaire.getAge()<70 && functionPriceCreditTermDayProductPrice(questionnaire)){
            return "Одобрено, на товар " + questionnaire.getProductDescription();
        }else if(questionnaire.getFloor().equals("Ж") && questionnaire.getAge()>20 && questionnaire.getAge()<60 && functionPriceCreditTermDayProductPrice(questionnaire)){
            return "Одобрено, на товар " + questionnaire.getProductDescription();
        }else return "Не одобрено, на товар " + questionnaire.getProductDescription();
    }

    public static boolean functionPriceCreditTermDayProductPrice(Questionnaire questionnaire){
        //цена товара/(днирасчета/30) < зарплата * (днирасчета/30);
        return questionnaire.getProductPrice() / questionnaire.getCredit_term_day()/30 < questionnaire.getIncome() * (questionnaire.getCredit_term_day()/30);
    }
}
