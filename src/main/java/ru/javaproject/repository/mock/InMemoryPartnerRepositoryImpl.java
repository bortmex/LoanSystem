package ru.javaproject.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javaproject.model.Partner;
import ru.javaproject.repository.PartnerRepository;
import ru.javaproject.util.PartnerUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryPartnerRepositoryImpl implements PartnerRepository {

    private Map<Integer, Partner> repository =  new ConcurrentHashMap<>();

    public static AtomicInteger counter = new AtomicInteger(0);

    public InMemoryPartnerRepositoryImpl() {
        PartnerUtil.PARTNERS.forEach(this::save);
    }

    @Override
    public Partner save(Partner partner, int partnerId) {
        try {
            if (repository.get(partner.getId()).getId()!= partnerId) return null;
        } catch (Exception ignored) {
        }

        if (partner.isNew()) {
            partner.setId(counter.incrementAndGet());
        }
        partner.setId(partnerId);
        repository.put(partner.getId(), partner);
        return partner;
    }

    public Partner save(Partner partner) {
        if (partner.isNew()) {
            partner.setId(counter.incrementAndGet());
        }
        repository.put(partner.getId(), partner);
        return partner;
    }

    @Override
    public boolean delete(int id, int partnerId) {
        if (repository.containsKey(id) && repository.get(id).getId() == partnerId) {
            repository.remove(id);
            return true;
        } else return false;
    }

    @Override
    public Partner get(int id, int partnerId) {
        if (repository.containsKey(id) && repository.get(id).getId() == partnerId) {
            return repository.get(id);
        } else return null;
    }

    @Override
    public Partner get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Partner> getAll(int partnerId) {
        List<Partner> list = repository.entrySet().stream().filter(meal -> meal.getValue().getId() == partnerId).map(Map.Entry::getValue).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Partner> getAll() {
        List<Partner> list = repository.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return list;
    }
}
