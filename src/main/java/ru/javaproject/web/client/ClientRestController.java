package ru.javaproject.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaproject.model.Client;
import ru.javaproject.service.ClientService;
import ru.javaproject.web.AuthorizedClient;

import java.util.List;

@Controller
public class ClientRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientService service;

    public List<Client> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public Client get() {
        LOG.info("get " + AuthorizedClient.id());
        return service.get(AuthorizedClient.id());
    }

    public Client create(Client client) {
        client.setId(null);
        LOG.info("create " + client);
        return service.save(client);
    }

    public void delete() {
        LOG.info("delete " + AuthorizedClient.id());
        service.delete(AuthorizedClient.id());
    }
}
