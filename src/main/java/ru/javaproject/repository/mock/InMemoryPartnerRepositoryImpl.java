package ru.javaproject.repository.mock;

import ru.javaproject.repository.mock.product.InMemoryProductPartner1Impl;
import ru.javaproject.repository.mock.product.InMemoryProductPartner2Impl;
import ru.javaproject.model.Partner;
import ru.javaproject.repository.PartnerRepository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPartnerRepositoryImpl implements PartnerRepository {

    private ConcurrentHashMap<Integer, Partner> listQuestionnaires =  new ConcurrentHashMap<>();

    public static AtomicInteger index = new AtomicInteger(0);

    public InMemoryPartnerRepositoryImpl() {
        add(new Partner("Партнер1", new InMemoryProductPartner1Impl()));
        add(new Partner("Партнер2", new InMemoryProductPartner2Impl()));
    }

    @Override
    public void add(Partner partner) {
        index.getAndIncrement();
        partner.setId(index.get());
        listQuestionnaires.put(index.get(),partner);
    }

    @Override
    public void update(Partner partner) {
        listQuestionnaires.put(partner.getId(),partner);
    }

    @Override
    public void remove(Partner id) {
        listQuestionnaires.remove(Integer.parseInt(id.toString()));
    }

    @Override
    public Partner getById(Integer id) {
        return listQuestionnaires.get(Integer.parseInt(id.toString()));
    }

    @Override
    public Collection<Partner> list() {
        return listQuestionnaires.values();
    }

    @Override
    public List<Partner> queryFindByName(String name) {
        return null;
    }
}
