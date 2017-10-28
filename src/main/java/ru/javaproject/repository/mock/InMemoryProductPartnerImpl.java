package ru.javaproject.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javaproject.model.Product;
import ru.javaproject.repository.ProductRepository;
import ru.javaproject.util.ProductUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryProductPartnerImpl implements ProductRepository {

    private Map<Integer, Product> repository =  new ConcurrentHashMap<>();
    public static AtomicInteger counter = new AtomicInteger(0);

    public InMemoryProductPartnerImpl() {
        ProductUtil.PRODUCTS.forEach(this::save);
    }

    @Override
    public Product save(Product product, int partnerId) {
        try {
            if (repository.get(product.getId()).getId()!= partnerId) return null;
        } catch (Exception ignored) {
        }

        if (product.isNew()) {
            product.setId(counter.incrementAndGet());
        }
        repository.put(product.getId(), product);
        return product;
    }

    @Override
    public Product save(Product product) {
        if (product.isNew()) {
            product.setId(counter.incrementAndGet());
        }
        repository.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean delete(int id, int partnerId) {
        if (repository.containsKey(id) && repository.get(id).getPartnerId() == partnerId) {
            repository.remove(id);
            return true;
        } else return false;
    }

    @Override
    public Product getById(Integer id, int partnerId) {
        if (repository.containsKey(id) && repository.get(id).getPartnerId() == partnerId) {
            return repository.get(id);
        } else return null;
    }

    @Override
    public List<Product> getAll(int partnerId) {
        List<Product> list = repository.entrySet().stream().filter(meal -> meal.getValue().getPartnerId() == partnerId).map(Map.Entry::getValue).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = repository.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return list;
    }

}
