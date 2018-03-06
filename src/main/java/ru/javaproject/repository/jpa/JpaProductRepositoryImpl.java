package ru.javaproject.repository.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.model.Product;
import ru.javaproject.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaProductRepositoryImpl implements ProductRepository{
    private static final Logger LOG = LoggerFactory.getLogger(JpaProductRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public List<Product> getAll(int partnerID) {
        return em.createNamedQuery(Product.ALL_WITH_USERID, Product.class).setParameter(1, partnerID).getResultList();
    }

    @Override
    public List<Product> getAll() {
        return em.createNamedQuery(Product.ALL, Product.class).getResultList();
    }
}
