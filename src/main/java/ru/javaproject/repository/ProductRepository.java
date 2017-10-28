package ru.javaproject.repository;

import ru.javaproject.model.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product, int partnerId);

    Product save(Product product);

    //void update(Product product);

    boolean delete(int id, int partnerId);

    Product getById(Integer id, int partnerId);

    List<Product> getAll(int partnerId);

    List<Product> getAll();
}
