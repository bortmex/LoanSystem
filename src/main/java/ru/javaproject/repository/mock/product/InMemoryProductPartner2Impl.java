package ru.javaproject.repository.mock.product;

import ru.javaproject.model.Product;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryProductPartner2Impl implements ProductRepository {

    private ConcurrentHashMap<Integer, Product> listQuestionnaires =  new ConcurrentHashMap<>();
    public static AtomicInteger index = new AtomicInteger(0);

    public InMemoryProductPartner2Impl() {
        add(new Product("MAZDA 458", 100000, 2));
        add(new Product("Стул", 5000, 2));
        add(new Product("Кофемашина MicroXperts [G120-04]", 1480, 2));
    }

    @Override
    public void add(Product product) {
        index.getAndIncrement();
        product.setId(index.get());
        listQuestionnaires.put(index.get(),product);
    }

    @Override
    public void update(Product product) {
        listQuestionnaires.put(product.getId(),product);
    }

    @Override
    public void remove(Product id) {
        listQuestionnaires.remove(Integer.parseInt(id.toString()));
    }

    @Override
    public Product getById(Integer id) {
        return listQuestionnaires.get(Integer.parseInt(id.toString()));
    }

    @Override
    public Collection<Product> list() {
        return listQuestionnaires.values();
    }

    @Override
    public List<Product> queryFindByName(String name) {
        return null;
    }
}
