package ru.javaproject.loansystem.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.repository.ProductRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryProductRepositoryImpl implements ProductRepository {

    private static final Comparator<Product> PRODUCT_COMPARATOR = Comparator.comparing(Product::getName).reversed();

    // Map  userId -> (mealId-> meal)
    private Map<Integer, Map<Integer, Product>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Product save(Product product, int partnerId) {
        if (product.isNew()) {
            product.setId(counter.incrementAndGet());
        } else if (get(product.getId(), partnerId) == null) {
            return null;
        }
        Map<Integer, Product> products = repository.computeIfAbsent(partnerId, ConcurrentHashMap::new);
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public boolean delete(int id, int partnerId) {
        Map<Integer, Product> products = repository.get(partnerId);
        return products != null && products.remove(id) != null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Product get(int id, int partnerId) {
        Map<Integer, Product> products = repository.get(partnerId);
        return products == null ? null : products.get(id);
    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public Collection<Product> getAll(int partnerId) {
        return getAllAsStream(partnerId).collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getAll() {
        return getAllAsStream().collect(Collectors.toList());
    }

    private Stream<Product> getAllAsStream(int partnerId) {
        Map<Integer, Product> meals = repository.get(partnerId);
        return meals == null ?
                Stream.empty() : meals.values().stream().sorted(PRODUCT_COMPARATOR);
    }

    private Stream<Product> getAllAsStream() {
        Map<Integer, Product> products = new HashMap<>();
        for (Map.Entry<Integer,Map<Integer,Product>> mapEntry: repository.entrySet()) {
            for (Map.Entry<Integer,Product> mpentry: mapEntry.getValue().entrySet()) {
                products.put(mpentry.getKey(),mpentry.getValue());
            }
        }
        return products == null ?
                Stream.empty() : products.values().stream().sorted(PRODUCT_COMPARATOR);
    }
}

