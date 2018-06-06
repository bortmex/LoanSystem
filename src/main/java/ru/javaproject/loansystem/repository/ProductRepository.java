package ru.javaproject.loansystem.repository;

import ru.javaproject.loansystem.model.Product;

import java.util.Collection;

public interface ProductRepository {

    Product save(Product product, int partnerId);

    Product save(Product product);

    boolean delete(int id, int partnerId);

    boolean delete(int id);

    Product get(int id, int userId);

    Product get(int id);

    Collection<Product> getAll(int partnerId);

    Collection<Product> getAll();
}
