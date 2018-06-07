package ru.javaproject.loansystem.web.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.View;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.to.UserTo;

import javax.validation.Valid;
import java.util.List;

import static ru.javaproject.loansystem.util.UsersUtil.strToRole;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.UI.class)
    public List<User> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.UI.class)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid UserTo userTo) {

        Role role = strToRole(userTo.getRoles());
        User user = new User(userTo.getId(), userTo.getName(), userTo.getEmail(), userTo.getPassword(), role);
        if (user.isNew()) {
            super.create(user);
        } else {
            super.update(userTo, userTo.getId());
        }
    }

    @PostMapping(value = "/{id}")
    public void enabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.enable(id, enabled);
    }
}