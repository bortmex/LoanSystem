package ru.javaproject.model;

import ru.javaproject.repository.mock.product.ProductRepository;

public class Partner {

    private Integer id;

    private final String name;

    private ProductRepository productDao;

    public Partner(String name, ProductRepository productDao) {
        this.name = name;
        this.productDao = productDao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ProductRepository getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductRepository productDao) {
        this.productDao = productDao;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productDao=" + productDao +
                '}';
    }
}
