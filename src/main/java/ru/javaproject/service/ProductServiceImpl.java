package ru.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.model.Product;
import ru.javaproject.repository.ProductRepository;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product, int partnerId) {
        return repository.save(product,partnerId);
    }

    @Override
    public void delete(int id, int partnerId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id,partnerId),id);
    }

    @Override
    public Product get(int id, int partnerId) throws NotFoundException {
        return checkNotFoundWithId(repository.getById(id, partnerId), id);
    }

    @Override
    public List<Product> getAll(int partnerId) {
        return repository.getAll(partnerId);
    }
}
