package ru.javaproject.util;

import ru.javaproject.model.Role;
import ru.javaproject.model.User;

import java.util.List;

public class UsersUtil {

    public static boolean isPartner(int partnerId, List<User> listUser){
        return listUser.stream().filter(x->x.getId()==partnerId).findFirst().get().getRoles().contains(Role.ROLE_PARTNER);
    }
}
