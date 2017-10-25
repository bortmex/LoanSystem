package ru.javaproject.model;

public class Product {

    private Integer id;

    private final String description;

    private final Integer price;

    private final Integer partnerId;

    public Product(String description, Integer price, Integer partnerId) {
        this.description = description;
        this.price = price;
        this.partnerId = partnerId;
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

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
