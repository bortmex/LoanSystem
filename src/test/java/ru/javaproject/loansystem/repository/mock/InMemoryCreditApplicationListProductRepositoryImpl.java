package ru.javaproject.loansystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.repository.CreditApplicationListProductRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryCreditApplicationListProductRepositoryImpl implements CreditApplicationListProductRepository{
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryCreditApplicationListProductRepositoryImpl.class);
    private Map<Integer, CreditApplicationListProduct> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public CreditApplicationListProduct save(CreditApplicationListProduct creditApplicationListProduct) {
        creditApplicationListProduct.setId(counter.incrementAndGet());
        repository.put(creditApplicationListProduct.getId(), creditApplicationListProduct);
        return creditApplicationListProduct;
    }

    @Override
    public boolean deleteAll(int creditApplicationId) {
        return repository.get(creditApplicationId) != null;
    }

    @Override
    public CreditApplicationListProduct get(int creditApplicationId) {
        return repository.get(creditApplicationId);
    }

    @Override
    public Collection<CreditApplicationListProduct> getAllForCreditApplicationId(int creditApplicationId) {
        return getAllAsStream(creditApplicationId).collect(Collectors.toList());
    }

    @Override
    public Collection<CreditApplicationListProduct> getAll() {
        return getAllAsStream().collect(Collectors.toList());
    }


    private static final Comparator<CreditApplicationListProduct> CREDIT_APPLICATION_LIST_PRODUCT_COMPARATOR = Comparator.comparing(CreditApplicationListProduct::getCredit_application_id).thenComparing(CreditApplicationListProduct::getProductl_id);

    @PostConstruct
    public void postConstruct() {
        LOG.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("+++ PreDestroy");
    }

    private Stream<CreditApplicationListProduct> getAllAsStream(int creditApplicationId) {
        return repository.values().stream().filter(x->x.getCredit_application_id()==creditApplicationId).sorted(CREDIT_APPLICATION_LIST_PRODUCT_COMPARATOR);
    }

    private Stream<CreditApplicationListProduct> getAllAsStream() {
        return repository.values().stream().sorted(CREDIT_APPLICATION_LIST_PRODUCT_COMPARATOR);
    }

}
