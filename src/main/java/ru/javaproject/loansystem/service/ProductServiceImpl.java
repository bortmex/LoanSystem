package ru.javaproject.loansystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.repository.ProductRepository;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.Collection;

import static ru.javaproject.loansystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product, int userId) {
        Assert.notNull(product, "product must not be null");
        return repository.save(product, userId);
    }

    @Override
    public Product get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
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
        Assert.notNull(product,"product must not be null");
        return checkNotFoundWithId(repository.save(product,userId), product.getId());
    }

}
