package ru.javaproject.loansystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        super.update(user, AuthorizedUser.id());
    }


    @GetMapping("/partner/create")
    public User createPartner() {
        return super.create(new User("","","", Role.ROLE_PARTNER));
    }

    @PostMapping(value = "/partner/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addPartner(@RequestBody User user) {
        Set<Role> set = new HashSet<>();
        set.add(Role.ROLE_PARTNER);
        user.setRoles(set);
        User created = super.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }

}
