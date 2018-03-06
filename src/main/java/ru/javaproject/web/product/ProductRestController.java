package ru.javaproject.web.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaproject.model.Product;
import ru.javaproject.service.ProductService;

import java.util.Collection;

@Controller
public class ProductRestController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductService service;

    public Collection<Product> getAll(int partnerId){
        return service.getAll(partnerId);
    }

    public Collection<Product> getAll(){
        return service.getAll();
    }
}
