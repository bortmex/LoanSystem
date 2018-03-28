package ru.javaproject.loansystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.repository.CreditApplicationRepository;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.Collection;

import static ru.javaproject.loansystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CreditApplicationServiceImpl implements CreditApplicationService {

    @Autowired
    private CreditApplicationRepository repository;

    @Override
    public CreditApplication get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Collection<CreditApplication> getAllForUsersId(int userId) {
        return repository.getAllForUsersId(userId);
    }

    @Override
    public Collection<CreditApplication> getAll() {
        return repository.getAll();
    }

    @Override
    public CreditApplication update(CreditApplication creditApplication, int userId) throws NotFoundException {
        Assert.notNull(creditApplication,"product must not be null");
        return checkNotFoundWithId(repository.save(creditApplication,userId), creditApplication.getId());
    }

    @Override
    public CreditApplication save(CreditApplication creditApplication, int userId) {
        Assert.notNull(creditApplication, "meal must not be null");
        return repository.save(creditApplication, userId);
    }
}
