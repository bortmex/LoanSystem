package ru.javaproject.repository;

import ru.javaproject.model.Product;

import java.util.Collection;

public interface ProductRepository {

    Product save(Product product);

    boolean delete(int id);

    Product get(int id);

    Collection<Product> getAll(int partnerId);

    Collection<Product> getAll();
}
