package ru.javaproject.web.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaproject.model.Product;
import ru.javaproject.service.ProductService;
import ru.javaproject.web.AuthorizedPartner;

import java.util.List;

@Controller
public class ProductRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    public List<Product> getAll(int partnerId) {
        LOG.info("getAll for " + partnerId);
        return service.getAll(partnerId);
    }

    public Product get(int id) {
        LOG.info("get " + id);
        return service.get(id, AuthorizedPartner.id());
    }

    public Product create(Product product, int patnerId) {
        LOG.info("create " + product);
        return service.save(product, patnerId);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, AuthorizedPartner.id());
    }
}
