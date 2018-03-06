package ru.javaproject.service;

import ru.javaproject.model.Product;
import ru.javaproject.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

public interface ProductService {
    Product get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<Product> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    Collection<Product> getAll(int partnerId);

    Collection<Product> getAll();

    Product update(Product product, int userId) throws NotFoundException;

    Product save(Product product, int userId);
}
