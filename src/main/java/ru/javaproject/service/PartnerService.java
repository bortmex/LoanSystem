package ru.javaproject.service;

import ru.javaproject.model.Partner;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

public interface PartnerService {
    Partner save(Partner partner, int partnerId);

    void delete(int id, int partnerId) throws NotFoundException;

    Partner get(int id, int partnerId) throws NotFoundException;

    Partner get(int id) throws NotFoundException;

    List<Partner> getAll(int id);

    List<Partner> getAll();

}
