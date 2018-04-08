package ru.javaproject.loansystem.web.creditapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.service.CreditApplicationService;
import ru.javaproject.loansystem.web.creaditapplicationlistproduct.AbstractCreditApplicationListProductRestController;

import java.util.Collection;

import static ru.javaproject.loansystem.util.ValidationUtil.checkIdConsistent;

public abstract class AbstractCreditApplicationRestController extends AbstractCreditApplicationListProductRestController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractCreditApplicationRestController.class);

    @Autowired
    private CreditApplicationService service;

    public Collection<CreditApplication> getAllForUsersId(int userId){
        LOG.info("getAll(userid) for User {}", userId);
        return service.getAllForUsersId(userId);
    }

    public CreditApplication create(CreditApplication creditApplication){
        LOG.info("create creditapplication {} for User {}", creditApplication, AuthorizedUser.id());
        return service.save(creditApplication, AuthorizedUser.id());
    }

    public void delete(int id, int userId){
        LOG.info("delete creditapplication {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public void update(CreditApplication creditApplication, int id) {
        checkIdConsistent(creditApplication, id);
        LOG.info("update creditapplication {} for User {}", creditApplication, AuthorizedUser.id());
        service.update(creditApplication, AuthorizedUser.id());
    }

    public CreditApplication get(int id,int userId){
        LOG.info("get creditapplication {} for User {}", id, userId);
        return service.get(id, userId);
    }


    public Collection<CreditApplication>  getAll(){
        LOG.info("getAll() for User {}", AuthorizedUser.id());
        return service.getAll();
    }
}
