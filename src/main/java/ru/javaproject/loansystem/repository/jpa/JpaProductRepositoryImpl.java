package ru.javaproject.loansystem.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaProductRepositoryImpl implements ProductRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Product save(Product product, int partnerId) {
        User ref = em.getReference(User.class, partnerId);
        product.setUser(ref);
        if(product.isNew()){
            em.persist(product);
            return product;
        }else {
            return em.createNamedQuery(Product.UPDATE).setParameter("id", product.getId()).setParameter("price", product.getPrice())
                                                      .setParameter("description", product.getDescription())
                                                      .setParameter(1, partnerId).executeUpdate() != 0 ? em.merge(product) : null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int partnerId) {
        return em.createNamedQuery(Product.DELETE).setParameter("id", id).setParameter(1, partnerId).executeUpdate() != 0;
    }

    @Override
    public Product get(int id, int userId) {
        Product product = em.find(Product.class, id);
        return product.getUser().getId()==userId ? product : null;
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
