package ru.javaproject.loansystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Product.ALL_WITH_USERID, query = "SELECT p FROM Product p where p.user.id = ?1 ORDER BY p.id"),
        @NamedQuery(name = Product.ALL, query = "SELECT p FROM Product p ORDER BY p.id"),
        @NamedQuery(name = Product.UPDATEWITHUSERID, query = "UPDATE Product p SET p.price=:price, p.description=:description WHERE p.id=:id and p.user.id=?1"),
        @NamedQuery(name = Product.UPDATE, query = "UPDATE Product p SET p.name=:name, p.price=:price, p.description=:description WHERE p.id=:id"),
        @NamedQuery(name = Product.DELETEWITHUSERID, query = "DELETE FROM Product p WHERE p.id=:id and p.user.id=?1"),
        @NamedQuery(name = Product.DELETE, query = "DELETE FROM Product p WHERE p.id=:id")
})

@Entity
@Table(name = "products")
@Access(AccessType.FIELD)
public class Product extends NamedEntity{

    public static final String UPDATEWITHUSERID = "Product.select.withuserid";
    public static final String UPDATE = "Product.select";
    public static final String DELETEWITHUSERID = "Product.delete.withuser.id";
    public static final String DELETE = "Product.delete";
    public static final String ALL_WITH_USERID = "Product.getAllWithUserID";
    public static final String ALL = "Product.getAll";

    public Product(){

    }

    public Product(Integer id, String name, int price, String description) {
        super(id, name);
        this.price = price;
        this.description = description;
    }

    public Product(Product product) {
        super(product.id, product.name);
        this.price = product.price;
        this.description = product.description;
    }

    public Product(String name, int price, String description) {
        super(null, name);
        this.price = price;
        this.description = description;
    }

    @Range(min = 10, max = 10000000)
    private Integer price;

    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)

    @JsonBackReference
    private Set<CreditApplication> creditApplicationSet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="partnerid", nullable = false)
    @JsonManagedReference
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPrice() {
        return price;
    }

    public Set<CreditApplication> getCreditApplicationSet() {
        return creditApplicationSet;
    }

    public void setCreditApplicationSet(Set<CreditApplication> creditApplicationSet) {
        this.creditApplicationSet = creditApplicationSet;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", description=" + description +
                '}';
    }
}
