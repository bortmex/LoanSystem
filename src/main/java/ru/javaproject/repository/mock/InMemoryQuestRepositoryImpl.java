package ru.javaproject.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javaproject.model.Questionnaire;
import ru.javaproject.repository.QuestRepository;
import ru.javaproject.util.QuestsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryQuestRepositoryImpl implements QuestRepository {

    private Map<Integer, Questionnaire> repository =  new ConcurrentHashMap<>();

    public static AtomicInteger counter = new AtomicInteger(0);

    {
        QuestsUtil.QUESTIONNAIRES.forEach(this::save);
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire, int partnerId) {
        try {
            if (repository.get(questionnaire.getId()).getPartnerId()!= partnerId) return null;
        } catch (Exception ignored) {
        }

        if (questionnaire.isNew()) {
            questionnaire.setId(counter.incrementAndGet());
        }
        questionnaire.setPartnerId(partnerId);
        repository.put(questionnaire.getId(), questionnaire);
        return questionnaire;
    }

    public Questionnaire save(Questionnaire questionnaire) {
        repository.put(questionnaire.getId(), questionnaire);
        return questionnaire;
    }

    @Override
    public boolean delete(int id, int partnerId) {
        if (repository.containsKey(id) && repository.get(id).getPartnerId() == partnerId) {
            repository.remove(id);
            return true;
        } else return false;
    }

    @Override
    public Questionnaire get(int id, int partnerId) {
        if (repository.containsKey(id) && repository.get(id).getPartnerId() == partnerId) {
            return repository.get(id);
        } else return null;
    }

    @Override
    public Questionnaire get(Integer id, int clientId) {
        if (repository.containsKey(id) && repository.get(id).getClientId() == clientId) {
            return repository.get(id);
        } else return null;
    }

    @Override
    public Collection<Questionnaire> getAll(int partnerId) {
        Collection<Questionnaire> questionnaires = repository.entrySet().stream().filter(quest -> quest.getValue().getPartnerId() == partnerId).map(Map.Entry::getValue).collect(Collectors.toList());
        return questionnaires;
    }

    @Override
    public Collection<Questionnaire> getAll(Integer clientId) {
        Collection<Questionnaire> questionnaires = repository.entrySet().stream().filter(quest -> quest.getValue().getClientId() == clientId).map(Map.Entry::getValue).collect(Collectors.toList());
        return questionnaires;
    }

    @Override
    public Collection<Questionnaire> getAll() {
        Collection<Questionnaire> questionnaires = repository.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return questionnaires;
    }
}
