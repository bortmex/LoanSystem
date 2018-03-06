package ru.javaproject.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.model.CreditApplication;
import ru.javaproject.repository.CreditApplicationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class CreditApplicationRepositoryImpl implements CreditApplicationRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CreditApplication save(CreditApplication creaditApplication) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public CreditApplication get(int id) {
        return null;
    }

    @Override
    public Collection<CreditApplication> getAllForUsersId(int userId) {
        return em.createNamedQuery(CreditApplication.ALL_SORTED, CreditApplication.class).setParameter(1, userId).getResultList();
    }
}
