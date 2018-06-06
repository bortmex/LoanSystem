package ru.javaproject.loansystem.web.creditapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.service.CreditApplicationService;
import ru.javaproject.loansystem.web.creaditapplicationlistproduct.AbstractCreditApplicationListProductController;

import java.util.Collection;

import static ru.javaproject.loansystem.util.ValidationUtil.checkIdConsistent;

public abstract class AbstractCreditApplicationController extends AbstractCreditApplicationListProductController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractCreditApplicationController.class);

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

    public void deleteCredApp(int id){
        LOG.info("delete creditapplication {}", id);
        service.delete(id);
    }

    public void update(CreditApplication creditApplication, int id) {
        checkIdConsistent(creditApplication, id);
        LOG.info("update creditapplication {} for User {}", creditApplication, AuthorizedUser.id());
        service.update(creditApplication, AuthorizedUser.id());
    }

    public void update(CreditApplication creditApplication) {
        LOG.info("update creditapplication {}", creditApplication);
        service.update(creditApplication);
    }

    public CreditApplication get(int id,int userId){
        LOG.info("get creditapplication {} for User {}", id, userId);
        return service.get(id, userId);
    }


    public CreditApplication get(int id){
        LOG.info("get creditapplication {}", id);
        return service.get(id);
    }


    public Collection<CreditApplication>  getAll(){
        LOG.info("getAll() for User {}", AuthorizedUser.id());
        return service.getAll();
    }

    public void enablePart(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "disable ") + id);
        service.enablePart(id, enabled);
    }

    public void enableRepr(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "disable ") + id);
        service.enableRepr(id, enabled);
    }
}
