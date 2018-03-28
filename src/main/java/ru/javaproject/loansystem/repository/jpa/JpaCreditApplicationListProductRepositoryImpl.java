package ru.javaproject.loansystem.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.repository.CreditApplicationListProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaCreditApplicationListProductRepositoryImpl implements CreditApplicationListProductRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public CreditApplicationListProduct save(CreditApplicationListProduct creditApplicationListProduct) {
        em.persist(creditApplicationListProduct);
        return creditApplicationListProduct;
    }

    @Override
    @Transactional
    public boolean deleteAll(int creditApplicationId) {
        return em.createNamedQuery(CreditApplicationListProduct.DELETE).setParameter("id", creditApplicationId).executeUpdate() != 0;
    }

    @Override
    public CreditApplicationListProduct get(int creditApplicationId) {
        return em.find(CreditApplicationListProduct.class, creditApplicationId);
    }

    @Override
    public Collection<CreditApplicationListProduct> getAllForCreditApplicationId(int creditApplicationId) {
        return em.createNamedQuery(CreditApplicationListProduct.ALL_SORTED_WITH_CREDAPPID, CreditApplicationListProduct.class).setParameter(1, creditApplicationId).getResultList();
    }

    @Override
    public Collection<CreditApplicationListProduct> getAll() {
        return em.createNamedQuery(CreditApplicationListProduct.ALL, CreditApplicationListProduct.class).getResultList();
    }
}
