package ru.javaproject.loansystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("roles") String roles) {


        Role role = Role.ROLE_USER;
        if(roles.equals(Role.ROLE_PARTNER.toString())) role = Role.ROLE_PARTNER;
        else if(roles.equals(Role.ROLE_REPRESENTATIVE.toString())) role = Role.ROLE_REPRESENTATIVE;
        User user = new User(id, name, email, password, role);
        if (user.isNew()) {
            super.create(user);
        } else {
            super.update(user, id);
        }
    }
}