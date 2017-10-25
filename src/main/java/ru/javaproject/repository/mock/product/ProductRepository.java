package ru.javaproject.repository.mock.product;

import ru.javaproject.model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    public void add(Product product);

    public void update(Product product);

    public void remove(Product id);

    public Product getById(Integer id);

    public Collection<Product> list();

    public List<Product> queryFindByName(String name);
}
