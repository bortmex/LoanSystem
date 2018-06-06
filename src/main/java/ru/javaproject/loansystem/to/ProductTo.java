package ru.javaproject.loansystem.to;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProductTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 2L;
    private Integer id;

    @Length.List({
            @Length(min = 3, message = "The field must be at least 3 characters"),
            @Length(max = 18, message = "The field must be less than 18 characters")
    })
    @NotBlank
    private String name;

    @NotNull
    @Range(min = 10, max = 10000000)
    private Integer price;

    @NotBlank
    private String description;

    public ProductTo() {
    }
    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
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
        return "ProductTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
