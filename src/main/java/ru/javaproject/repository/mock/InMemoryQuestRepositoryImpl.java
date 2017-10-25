package ru.javaproject.repository.mock;

import ru.javaproject.model.Questionnaire;
import ru.javaproject.repository.QuestRepository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryQuestRepositoryImpl implements QuestRepository {

    private ConcurrentHashMap<Integer, Questionnaire> listQuestionnaires =  new ConcurrentHashMap<>();

    public static AtomicInteger index = new AtomicInteger(0);

    public InMemoryQuestRepositoryImpl() {
        add(new Questionnaire(true,24,35000, "Volga", 45000, 60 , 1));
        add(new Questionnaire(false,56,56000,"UFO", 45,30, 1));
        add(new Questionnaire(true,27,78000,  "raketa", 450000, 55, 1));
    }

    @Override
    public void add(Questionnaire questionnaire) {
        index.getAndIncrement();
        questionnaire.setId(index.get());
        listQuestionnaires.put(index.get(),questionnaire);
    }

    @Override
    public void update(Questionnaire questionnaire) {
        listQuestionnaires.put(questionnaire.getId(),questionnaire);
    }

    @Override
    public void remove(Questionnaire id) {
        listQuestionnaires.remove(Integer.parseInt(id.toString()));
    }

    @Override
    public Questionnaire getById(Integer id) {
        return listQuestionnaires.get(Integer.parseInt(id.toString()));
    }

    @Override
    public Collection<Questionnaire> list() {
        return listQuestionnaires.values();
    }

    @Override
    public List<Questionnaire> queryFindByName(String name) {
        return null;
    }
}
