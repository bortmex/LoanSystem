package ru.javaproject.model;

public class Questionnaire extends BaseEntity{

    private Integer clientId;
    //пол
    private String floor;

    private int age;
    //доход
    private int income;

    private String productDescription;

    private int productPrice;
    //срок кредита
    private int credit_term_day;

    private int partnerId;

    private int decisionPartner;

    private int decisionRepresentative;

    public Questionnaire(String floor, int clientId, int age, int income, String productDescription, int productPrice, int credit_term_day, int partnerId) {
        this(null, clientId, floor,age, income, productDescription, productPrice, credit_term_day, partnerId);
    }

    public Questionnaire(Integer id, int clientId, String floor, int age, int income, String productDescription, int productPrice, int credit_term_day, int partnerId) {
        this.id = id;
        this.clientId = clientId;
        this.floor = floor;
        this.age = age;
        this.income = income;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.credit_term_day = credit_term_day;
        this.partnerId = partnerId;
        this.decisionPartner = 0;
        this.decisionRepresentative = 0;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getCredit_term_day() {
        return credit_term_day;
    }

    public void setCredit_term_day(int credit_term_day) {
        this.credit_term_day = credit_term_day;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public int getDecisionPartner() {
        return decisionPartner;
    }

    public void setDecisionPartner(int decisionPartner) {
        this.decisionPartner = decisionPartner;
    }

    public int getDecisionRepresentative() {
        return decisionRepresentative;
    }

    public void setDecisionRepresentative(int decisionRepresentative) {
        this.decisionRepresentative = decisionRepresentative;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "clientId=" + clientId +
                ", floor=" + floor +
                ", age=" + age +
                ", income=" + income +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", credit_term_day=" + credit_term_day +
                ", partnerId=" + partnerId +
                ", decisionPartner=" + decisionPartner +
                ", decisionRepresentative=" + decisionRepresentative +
                '}';
    }
}
