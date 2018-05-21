package ru.javaproject.loansystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.util.UsersUtil;

import java.util.List;

@RestController
@RequestMapping("/ajax/repres/partner")
public class RepresAjaxController  extends AbstractUserController  {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllPartner() {
        return UsersUtil.getAllPartnerList(super.getAll());
    }

    @PostMapping
    public void createPartner( @RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, name, email, password, Role.ROLE_PARTNER);
        super.create(user);
    }
}
