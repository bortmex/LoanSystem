package ru.javaproject.loansystem.service;


import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void evictCache();
}
