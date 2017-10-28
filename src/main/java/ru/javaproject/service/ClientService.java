package ru.javaproject.service;

import ru.javaproject.model.Client;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

public interface ClientService {
    Client save(Client client);

    void delete(int id) throws NotFoundException;

    Client get(int id) throws NotFoundException;

    Client getByEmail(String email) throws NotFoundException;

    List<Client> getAll();

    /*void update(Client client);*/
}
