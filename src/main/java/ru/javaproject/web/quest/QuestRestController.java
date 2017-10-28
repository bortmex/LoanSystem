package ru.javaproject.web.quest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaproject.model.Questionnaire;
import ru.javaproject.service.QuestService;
import ru.javaproject.web.AuthorizedPartner;

import java.util.List;

@Controller
public class QuestRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final QuestService service;

    public QuestRestController(QuestService questService){
        this.service = questService;
    }

    public List<Questionnaire> getAll(int partnerId) {
        LOG.info("getAll");
        return service.getAll(partnerId);
    }


    public List<Questionnaire> getAll(Integer clientId) {
        LOG.info("getAll");
        return service.getAll(clientId);
    }

    public List<Questionnaire> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public Questionnaire save(Questionnaire questionnaire, int partnerId) {
        LOG.info("save");
        return service.save(questionnaire,partnerId);
    }

    public Questionnaire update(Questionnaire questionnaire, int partnerId) {
        LOG.info("update");
        return service.update(questionnaire,partnerId);
    }



    public Questionnaire get(int partnerId) {
        LOG.info("get " + partnerId);
        return service.get(partnerId, AuthorizedPartner.id());
    }

    public Questionnaire create(Questionnaire meal) {
        meal.setId(null);
        LOG.info("create " + meal);
        return service.save(meal, AuthorizedPartner.id());
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, AuthorizedPartner.id());
    }

}
