package ru.javaproject.loansystem.web.product;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.service.ProductService;

import java.util.Collection;

import static ru.javaproject.loansystem.util.ValidationUtil.checkIdConsistent;
import static ru.javaproject.loansystem.util.ValidationUtil.checkNew;

@Controller
public class ProductRestController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductService service;

    public Product get(int id){
        LOG.info("get product {} for Partner {}", id, AuthorizedUser.id());
        return service.get(id, AuthorizedUser.id());
    }

    public void delete(int id) {
        LOG.info("delete product {} for Partner {}", id, AuthorizedUser.id());
        service.delete(id, AuthorizedUser.id());
    }

    public Product create(Product product){
        checkNew(product);
        LOG.info("create product {} for Partner {}", product, AuthorizedUser.id());
        return service.save(product, AuthorizedUser.id());
    }

    public void update(Product product, int id) {
        checkIdConsistent(product, id);
        LOG.info("update {} for Partner {}", product, AuthorizedUser.id());
        service.update(product, AuthorizedUser.id());
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
