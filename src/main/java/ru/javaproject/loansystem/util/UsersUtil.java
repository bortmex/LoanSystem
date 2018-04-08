package ru.javaproject.loansystem.util;

import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UsersUtil {

    public static boolean isPartnerWithList(int partnerId, List<User> listUser){
        return listUser.stream().filter(x->x.getId()==partnerId).findFirst().get().getRoles().contains(Role.ROLE_PARTNER);
    }

    public static List<User> getAllPartnerList(List<User> listUser){
        return listUser.stream().filter(x->x.getRoles().contains(Role.ROLE_PARTNER)).collect(Collectors.toList());
    }


    public static boolean isPartner(User user){
        return user.getRoles().contains(Role.ROLE_PARTNER);
    }
    public static boolean isAdmin(User user){
        return user.getRoles().contains(Role.ROLE_ADMIN);
    }


    public static boolean isRepresentative(User user){
        return user.getRoles().contains(Role.ROLE_REPRESENTATIVE);
    }
}
