package ru.javaproject.service;

import ru.javaproject.model.Product;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

public interface ProductService {
    Product save(Product product, int partnerId);

    void delete(int id, int partnerId) throws NotFoundException;

    Product get(int id, int partnerId) throws NotFoundException;

    List<Product> getAll(int partnerId);
}
