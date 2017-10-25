package ru.javaproject.model;

public class Questionnaire {

    private Integer id;
    //пол
    private boolean floor;

    private Integer age;
    //доход
    private Integer income;

    private String productDescription;

    private Integer productPrice;
    //срок кредита
    private Integer credit_term_day;

    private Integer partnerId;

    public Questionnaire(boolean floor, Integer age, Integer income, String productDescription, Integer productPrice, Integer credit_term_day, Integer partnerId) {
        this.floor = floor;
        this.age = age;
        this.income = income;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.credit_term_day = credit_term_day;
        this.partnerId = partnerId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isFloor() {
        return floor;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getIncome() {
        return income;
    }

    public Integer getCredit_term_day() {
        return credit_term_day;
    }

    public void setFloor(boolean floor) {
        this.floor = floor;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public void setCredit_term_day(Integer credit_term_day) {
        this.credit_term_day = credit_term_day;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", floor=" + floor +
                ", age=" + age +
                ", income=" + income +
                ", credit_term_day=" + credit_term_day +
                '}';
    }
}
