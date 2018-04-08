package ru.javaproject.loansystem.web.creaditapplicationlistproduct;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.service.CreditApplicationListProductService;

import java.util.Collection;


public abstract class AbstractCreditApplicationListProductRestController {

    private static final Logger LOG = LoggerFactory.getLogger(CreditApplicationListProductRestController.class);

    @Autowired
    private CreditApplicationListProductService service;

    public Collection<CreditApplicationListProduct> getAllForCreditApplicationListProduct(int crediatAppId){
        LOG.info("getAllForCreditApplication for CreditApplicationListProduct {}",crediatAppId);
        return service.getAllForCreditApplicationId(crediatAppId);
    }

    public Collection<CreditApplicationListProduct> getAllCALP(){
        LOG.info("getAll");
        return service.getAll();
    }

    public CreditApplicationListProduct get(int creditApplicationId){
        LOG.info("get CreditApplicationListProduct {}", creditApplicationId);
        return service.get(creditApplicationId);
    }

    public CreditApplicationListProduct create(CreditApplicationListProduct creditApplicationListProduct){
        LOG.info("create CreditApplicationListProduct {}", creditApplicationListProduct);
        return service.save(creditApplicationListProduct);
    }

    public void delete(int id){
        LOG.info("delete CreditApplicationListProduct {}", id);
        service.delete(id);
    }
}
