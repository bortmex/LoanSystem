package ru.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.model.CreditApplication;
import ru.javaproject.repository.CreditApplicationRepository;
import ru.javaproject.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class CreditApplicationServiceImpl implements CreditApplicationService {

    @Autowired
    private CreditApplicationRepository repository;

    @Override
    public CreditApplication get(int id, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Collection<CreditApplication> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }

    @Override
    public Collection<CreditApplication> getAllForUsersId(int userId) {
        return repository.getAllForUsersId(userId);
    }

    @Override
    public CreditApplication update(CreditApplication creditApplication, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public CreditApplication save(CreditApplication creditApplication, int userId) {
        return null;
    }
}
