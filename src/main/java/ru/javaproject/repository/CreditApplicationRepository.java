package ru.javaproject.repository;


import ru.javaproject.model.CreditApplication;

import java.util.Collection;

public interface CreditApplicationRepository {

    CreditApplication save(CreditApplication creaditApplication);

    boolean delete(int id);

    CreditApplication get(int id);

    Collection<CreditApplication> getAllForUsersId(int userId);
}
