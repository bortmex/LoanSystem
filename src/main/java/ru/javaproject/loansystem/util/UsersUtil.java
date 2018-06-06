package ru.javaproject.loansystem.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.to.UserTo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersUtil {

    public static List<User> getAllPartnerList(List<User> listUser){
        return listUser.stream().filter(x->x.getRoles().contains(Role.ROLE_PARTNER)).collect(Collectors.toList());
    }

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        Set<Role> setRolus = new HashSet<>();
        setRolus.add(strToRole(userTo.getRoles()));
        user.setRoles(setRolus);
        return user;
    }

    public static Role strToRole(String text){
        if(text.contains("[")) text= text.substring(1,text.length()-1);
        Role role = Role.ROLE_USER;
        if(text.equals(Role.ROLE_PARTNER.toString())) role = Role.ROLE_PARTNER;
        else if(text.equals(Role.ROLE_REPRESENTATIVE.toString())) role = Role.ROLE_REPRESENTATIVE;
        else if(text.equals(Role.ROLE_ADMIN.toString())) role = Role.ROLE_ADMIN;
        return role;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles().toString());
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
