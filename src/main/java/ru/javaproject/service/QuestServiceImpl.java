package ru.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.model.Questionnaire;
import ru.javaproject.repository.QuestRepository;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class QuestServiceImpl implements QuestService {

    private final QuestRepository repository;

    @Autowired
    public QuestServiceImpl(QuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire, int partnerId) {
        return repository.save(questionnaire, partnerId);
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire, int partnerId) {
        return checkNotFoundWithId(repository.save(questionnaire,partnerId),partnerId);
    }

    @Override
    public void delete(int id, int partnerId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id,partnerId),id);
    }

    @Override
    public Questionnaire get(int id, int partnerId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, partnerId), id);
    }

    @Override
    public List<Questionnaire> getAll(int partnerId) {
        return (List<Questionnaire>)repository.getAll((partnerId));
    }

    @Override
    public List<Questionnaire> getAll(Integer clientId) {
        return (List<Questionnaire>)repository.getAll(clientId);
    }
    @Override
    public List<Questionnaire> getAll() {
        return (List<Questionnaire>)repository.getAll();
    }
}
