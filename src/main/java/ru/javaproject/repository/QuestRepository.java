package ru.javaproject.repository;

import ru.javaproject.model.Questionnaire;

import java.util.Collection;

public interface QuestRepository {
    Questionnaire save(Questionnaire questionnaire, int partnerId);

    boolean delete(int id, int partnerId);

    Questionnaire get(int id, int partnerId);

    Questionnaire get(Integer id, int clientId);

    Collection<Questionnaire> getAll(int partnerId);

    Collection<Questionnaire> getAll();

    Collection<Questionnaire> getAll(Integer clientId);
}
