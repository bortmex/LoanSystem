package ru.javaproject.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javaproject.model.Client;
import ru.javaproject.repository.ClientRepository;
import ru.javaproject.util.ClientsUtil;
import ru.javaproject.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryClientRepositoryImpl implements ClientRepository{
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryClientRepositoryImpl.class);
    private Map<Integer, Client> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        ClientsUtil.CLIENTS.forEach(this::save);
    }

    @Override
    public Client save(Client client) {
        if (client.isNew()) {
            client.setId(counter.incrementAndGet());
        }
        repository.put(client.getId(), client);
        return client;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Client get(int id) {
        return repository.get(id);
    }

    @Override
    public Client getByEmail(String email) {
        Client client;
        try {
            client =  repository.entrySet().stream().filter(s -> s.getValue().getEmail().equals(email)).findFirst().get().getValue();
        }catch (NoSuchElementException e){
            throw new NotFoundException("Не найдено");
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clientList = new ArrayList<>(repository.values());
        Collections.sort(clientList, (a, b) -> a.getName().compareTo(b.getName()));
        return clientList;
    }
}
