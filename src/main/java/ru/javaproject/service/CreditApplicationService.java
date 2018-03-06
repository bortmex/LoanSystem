package ru.javaproject.service;

import ru.javaproject.model.CreditApplication;
import ru.javaproject.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

public interface CreditApplicationService {
    CreditApplication get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Collection<CreditApplication> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    Collection<CreditApplication> getAllForUsersId(int userId);

    CreditApplication update(CreditApplication creditApplication, int userId) throws NotFoundException;

    CreditApplication save(CreditApplication creditApplication, int userId);
}
