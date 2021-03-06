package ru.javaproject.loansystem.web.product;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.service.ProductService;

import java.util.Collection;

import static ru.javaproject.loansystem.util.ValidationUtil.checkIdConsistent;
import static ru.javaproject.loansystem.util.ValidationUtil.checkNew;

public abstract class AbstractProductController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractProductController.class);

    @Autowired
    private ProductService service;

    public Product get(int id){
        LOG.info("get product {} for Partner {}", id, AuthorizedUser.id());
        return service.get(id);
    }

    public Product get(int id, int partnerId){
        LOG.info("get product {} for Partner {}", id, partnerId);
        return service.get(id, partnerId);
    }

    public void delete(int id) {
        LOG.info("delete product {} for Partner {}", id, AuthorizedUser.id());
        service.delete(id);
    }

    public Product create(Product product, int idpartner){
        checkNew(product);
        LOG.info("create product {} for Partner {}", product, idpartner);
        return service.save(product, idpartner);
    }

    public void update(Product product, int id) {
        checkIdConsistent(product, id);
        LOG.info("update {} for Partner {}", product, AuthorizedUser.id());
        service.update(product, AuthorizedUser.id());
    }

    public void update(Product product) {
        LOG.info("update {}", product);
        service.update(product);
    }

    public Collection<Product> getAll(int partnerId){
        LOG.info("getAll(partnerid) for Partner {}", partnerId);
        return service.getAll(partnerId);
    }

    public Collection<Product> getAll(){
        LOG.info("getAll() for Partner {}", AuthorizedUser.id());
        return service.getAll();
    }
}
