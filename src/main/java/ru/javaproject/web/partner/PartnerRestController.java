package ru.javaproject.web.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaproject.model.Partner;
import ru.javaproject.service.PartnerService;
import ru.javaproject.web.AuthorizedPartner;

import java.util.List;

@Controller
public class PartnerRestController{
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final PartnerService service;

    public PartnerRestController(PartnerService service) {
        this.service = service;
    }

    public List<Partner> getAll(int partnerId) {
        LOG.info("getAll for " + partnerId);
        return service.getAll(partnerId);
    }

    public List<Partner> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public Partner get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public Partner create(Partner partner) {
        LOG.info("create " + partner);
        return service.save(partner, partner.getId());
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, AuthorizedPartner.id());
    }

}
