package ru.javaproject.service;

import ru.javaproject.model.Questionnaire;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

public interface QuestService {

    Questionnaire save(Questionnaire questionnaire, int partnerId);

    Questionnaire update(Questionnaire questionnaire, int partnerId);

    void delete(int id, int partnerId) throws NotFoundException;

    Questionnaire get(int id, int partnerId) throws NotFoundException;

    List<Questionnaire> getAll(int partnerId);

    List<Questionnaire> getAll();

    List<Questionnaire> getAll(Integer clientId);

    //void update(Questionnaire questionnaire, int partnerId);
}
