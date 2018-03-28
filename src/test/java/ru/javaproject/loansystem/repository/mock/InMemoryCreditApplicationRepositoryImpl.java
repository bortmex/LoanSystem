package ru.javaproject.loansystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.repository.CreditApplicationRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryCreditApplicationRepositoryImpl implements CreditApplicationRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryCreditApplicationRepositoryImpl.class);

    private Map<Integer, Map<Integer, CreditApplication>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Comparator<CreditApplication> CREDIT_APPLICATION_COMPARATOR = Comparator.comparing(CreditApplication::getFio).thenComparing(CreditApplication::getPhoneNumber);

    @Override
    public CreditApplication save(CreditApplication creditApplication, int userId) {
        if (creditApplication.isNew()) {
            creditApplication.setId(counter.incrementAndGet());
        } else if (get(creditApplication.getId(), userId) == null) {
            return null;
        }
        Map<Integer, CreditApplication> creditapplications = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        creditapplications.put(creditApplication.getId(), creditApplication);
        return creditApplication;
    }

    @PostConstruct
    public void postConstruct() {
        LOG.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, CreditApplication> creditApplications = repository.get(userId);
        return creditApplications != null && creditApplications.remove(id) != null;
    }

    @Override
    public CreditApplication get(int id, int userId) {
        Map<Integer, CreditApplication> products = repository.get(userId);
        return products == null ? null : products.get(id);
    }

    @Override
    public Collection<CreditApplication> getAllForUsersId(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    @Override
    public Collection<CreditApplication> getAll() {
        return getAllAsStream().collect(Collectors.toList());
    }

    private Stream<CreditApplication> getAllAsStream(int userId) {
        Map<Integer, CreditApplication> meals = repository.get(userId);
        return meals == null ?
                Stream.empty() : meals.values().stream().sorted(CREDIT_APPLICATION_COMPARATOR);
    }

    private Stream<CreditApplication> getAllAsStream() {
        Map<Integer, CreditApplication> creditApplicationMap = new HashMap<>();
        for (Map.Entry<Integer,Map<Integer,CreditApplication>> mapEntry: repository.entrySet()) {
            for (Map.Entry<Integer,CreditApplication> mpentry: mapEntry.getValue().entrySet()) {
                creditApplicationMap.put(mpentry.getKey(),mpentry.getValue());
            }
        }
        return creditApplicationMap == null ?
                Stream.empty() : creditApplicationMap.values().stream().sorted(CREDIT_APPLICATION_COMPARATOR);
    }
}
