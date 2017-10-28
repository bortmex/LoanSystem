package ru.javaproject.model;

public class Product extends NamedEntity{

    private final String description;

    private final int price;

    private final int partnerId;

    public Product(String description, int price, int partnerId) {
        this.description = description;
        this.price = price;
        this.partnerId = partnerId;
    }

    public int getPartnerId() {
        return partnerId;
    }


    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", partnerId=" + partnerId +
                '}';
    }
}
