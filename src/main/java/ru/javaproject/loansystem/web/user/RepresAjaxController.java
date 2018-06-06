package ru.javaproject.loansystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.to.UserTo;
import ru.javaproject.loansystem.util.UsersUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/repres/part")
public class RepresAjaxController  extends AbstractUserController  {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllPartner() {
        return UsersUtil.getAllPartnerList(super.getAll());
    }

    @PostMapping
    public void createPartner(@Valid UserTo userTo) {
        User user = new User(userTo.getId(), userTo.getName(), userTo.getEmail(), userTo.getPassword(), Role.ROLE_PARTNER);
        super.create(user);
    }
}
