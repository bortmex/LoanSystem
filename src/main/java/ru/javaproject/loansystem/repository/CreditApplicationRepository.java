package ru.javaproject.loansystem.repository;


import ru.javaproject.loansystem.model.CreditApplication;

import java.util.Collection;

public interface CreditApplicationRepository {

    CreditApplication save(CreditApplication creaditApplication, int userId);

    CreditApplication save(CreditApplication creaditApplication);

    boolean delete(int id, int userId);

    boolean delete(int id);

    CreditApplication get(int id, int userId);

    CreditApplication get(int id);

    Collection<CreditApplication> getAllForUsersId(int userId);

    Collection<CreditApplication> getAll();
}
