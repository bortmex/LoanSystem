package ru.javaproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.model.Product;
import ru.javaproject.repository.ProductRepository;
import ru.javaproject.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repository;

    @Override
    public Product get(int id, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Collection<Product> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }

    @Override
    public Collection<Product> getAll(int partnerId) {
        return repository.getAll(partnerId);
    }

    @Override
    public Collection<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public Product update(Product product, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public Product save(Product product, int userId) {
        return null;
    }
}
