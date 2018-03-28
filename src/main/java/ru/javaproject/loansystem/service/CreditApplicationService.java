package ru.javaproject.loansystem.service;

import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.Collection;

public interface CreditApplicationService {
    CreditApplication get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<CreditApplication> getAllForUsersId(int userId);

    Collection<CreditApplication> getAll();

    CreditApplication update(CreditApplication creditApplication, int userId) throws NotFoundException;

    CreditApplication save(CreditApplication creditApplication, int userId);
}
