package ru.javaproject.repository;

import java.util.Collection;
import java.util.List;

public interface PartnerRepository {

    public void add(ru.javaproject.model.Partner partner);

    public void update(ru.javaproject.model.Partner partner);

    public void remove(ru.javaproject.model.Partner id);

    public ru.javaproject.model.Partner getById(Integer id);

    public Collection<ru.javaproject.model.Partner> list();

    public List<ru.javaproject.model.Partner> queryFindByName(String name);
}
