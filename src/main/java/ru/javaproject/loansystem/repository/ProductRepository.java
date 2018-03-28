package ru.javaproject.loansystem.repository;

import ru.javaproject.loansystem.model.Product;

import java.util.Collection;

public interface ProductRepository {

    Product save(Product product, int partnerId);

    boolean delete(int id, int partnerId);

    Product get(int id, int userId);

    Collection<Product> getAll(int partnerId);

    Collection<Product> getAll();
}
