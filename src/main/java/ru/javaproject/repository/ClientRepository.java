package ru.javaproject.repository;

import ru.javaproject.model.Client;

import java.util.List;

public interface ClientRepository {
    Client save(Client client);

    // false if not found
    boolean delete(int id);

    // null if not found
    Client get(int id);

    // null if not found
    Client getByEmail(String email);

    List<Client> getAll();
}
