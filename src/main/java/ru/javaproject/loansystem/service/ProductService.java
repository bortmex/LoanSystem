package ru.javaproject.loansystem.service;

import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.Collection;

public interface ProductService {
    Product get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Product> getAll(int partnerId);

    Collection<Product> getAll();

    Product update(Product product, int userId) throws NotFoundException;

    Product save(Product product, int userId);
}
