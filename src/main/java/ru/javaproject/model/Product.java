package ru.javaproject.model;


import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name = Product.ALL_WITH_USERID, query = "SELECT p FROM Product p where p.partnerId = ?1"),
        @NamedQuery(name = Product.ALL, query = "SELECT p FROM Product p")
})

@Entity
@Table(name = "products")
public class Product extends NamedEntity{

    public static final String UPDATE = "Product.select";
    public static final String DELETE = "Product.delete";
    public static final String ALL_WITH_USERID = "Product.getAllWithUserID";
    public static final String ALL = "Product.getAll";
    public static final String ALL_BETWEEN = "Product.getAllBetween";

    public Product(){

    }

    public Product(Integer id, String name, int price, int partnerId) {
        super(id, name);
        this.price = price;
        this.partnerId = partnerId;
    }

    private int price;

    private int partnerId;

    protected Product(Integer id, String name) {
        super(id, name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "Products{" +
                "partnerId=" + partnerId +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
