package ru.javaproject.repository;

import ru.javaproject.model.Questionnaire;

import java.util.Collection;
import java.util.List;

public interface QuestRepository {
    public void add(Questionnaire questionnaire);

    public void update(Questionnaire questionnaire);

    public void remove(Questionnaire id);

    public Questionnaire getById(Integer id);

    public Collection<Questionnaire> list();

    public List<Questionnaire> queryFindByName(String name);
}
