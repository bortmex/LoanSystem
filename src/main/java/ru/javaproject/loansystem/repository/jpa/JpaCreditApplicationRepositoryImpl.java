package ru.javaproject.loansystem.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.repository.CreditApplicationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaCreditApplicationRepositoryImpl implements CreditApplicationRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public CreditApplication save(CreditApplication creditapplication) {
        if (creditapplication.isNew()) {
            em.persist(creditapplication);
            return creditapplication;
        } else {
            return em.merge(creditapplication);
        }
    }

    @Override
    @Transactional
    public CreditApplication save(CreditApplication creaditApplication, int userId) {
        User ref = em.getReference(User.class, userId);
        creaditApplication.setUser(ref);
        if(creaditApplication.isNew()){
            em.persist(creaditApplication);
            return creaditApplication;
        }else {
            return em.createNamedQuery(CreditApplication.UPDATE).setParameter("id", creaditApplication.getId()).setParameter("fio", creaditApplication.getFio())
                    .setParameter("dateBirth", creaditApplication.getDateBirth())
                    .setParameter("dateTimeCreate", LocalDateTime.now()).setParameter("phoneNumber", creaditApplication.getPhoneNumber())
                    .setParameter("anInitialFee", creaditApplication.getAnInitialFee())
                    .setParameter(1, userId).executeUpdate() != 0 ? em.merge(creaditApplication) : null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(CreditApplication.DELETE).setParameter("id", id).setParameter(1,userId).executeUpdate() != 0;
    }

    @Override
    public CreditApplication get(int id) {
        return em.find(CreditApplication.class, id);
    }

    @Override
    public CreditApplication get(int id, int userId) {
        CreditApplication creditApplication = em.find(CreditApplication.class, id);/*
        User ref = em.getReference(User.class, userId);
        creditApplication.setUser(ref);*/
        return creditApplication.getUser().getId()==userId ? creditApplication : null;
    }

    @Override
    public Collection<CreditApplication> getAllForUsersId(int userId) {
        return em.createNamedQuery(CreditApplication.ALL_WHERE_USER_ID, CreditApplication.class).setParameter(1, userId).getResultList();
    }

    @Override
    public Collection<CreditApplication> getAll() {
        return em.createNamedQuery(CreditApplication.ALL, CreditApplication.class).getResultList();
    }
}
