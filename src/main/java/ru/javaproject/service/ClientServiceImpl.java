package ru.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.model.Client;
import ru.javaproject.repository.ClientRepository;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.util.ValidationUtil.checkNotFound;
import static ru.javaproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Client get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Client getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<Client> getAll()  {
        return repository.getAll();
    }

   /* @Override
    public void update(Client client) {
        checkNotFoundWithId(repository.save(client), client.getClientId());
    }*/
}
