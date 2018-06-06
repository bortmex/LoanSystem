package ru.javaproject.loansystem.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.service.UserService;
import ru.javaproject.loansystem.to.UserTo;

import java.util.List;

import static ru.javaproject.loansystem.util.ValidationUtil.checkIdConsistent;
import static ru.javaproject.loansystem.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        checkNew(user);
        log.info("create " + user);
        return service.save(user);
    }

    public void update(UserTo userTo) {
        log.info("update " + userTo);
        checkIdConsistent(userTo, AuthorizedUser.id());
        service.update(userTo);
    }


    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        checkIdConsistent(user, id);
        log.info("update " + user);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update " + userTo);
        checkIdConsistent(userTo, id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail " + email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + id);
        service.enable(id, enabled);
    }
}