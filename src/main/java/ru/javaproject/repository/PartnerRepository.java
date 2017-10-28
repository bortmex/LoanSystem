package ru.javaproject.repository;

import ru.javaproject.model.Partner;

import java.util.List;

public interface PartnerRepository {

    Partner save(Partner partner, int partnerId);

    boolean delete(int id, int partnerId);

    Partner get(int id, int partnerId);

    Partner get(int id);

    List<Partner> getAll(int partnerId);

    List<Partner> getAll();

}
