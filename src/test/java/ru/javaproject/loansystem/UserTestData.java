package ru.javaproject.loansystem;


import ru.javaproject.loansystem.matcher.ModelMatcher;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;

import java.util.Objects;

import static ru.javaproject.loansystem.model.BaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER_NEW_ID = START_SEQ + 20;
    public static final int ADMIN_ID = START_SEQ + 2;
    public static final int PARTNER1_ID = START_SEQ + 3;
    public static final int PARTNER2_ID = START_SEQ + 4;
    public static final int PARTNER3_ID = START_SEQ + 5;
    public static final int REPRESENTATIVE_ID = START_SEQ + 6;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User USER1 = new User(USER1_ID, "User1", "user1@yandex1.ru", "password1", Role.ROLE_USER);
    public static final User USER_NEW = new User(USER_NEW_ID, "New", "new@gmail.com", "newPass",Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final User PARTNER1 = new User(PARTNER1_ID, "Partner1", "partner@yandex.ru", "partner1", Role.ROLE_PARTNER);
    public static final User PARTNER2 = new User(PARTNER2_ID, "Partner2", "partner1@yandex.ru", "partner2", Role.ROLE_PARTNER);
    public static final User PARTNER3 = new User(PARTNER3_ID, "Partner3", "partner2@yandex.ru", "partner3", Role.ROLE_PARTNER);
    public static final User REPRESENTATIVE = new User(REPRESENTATIVE_ID, "Representative", "representative@gmail.com", "representative", Role.ROLE_REPRESENTATIVE);


    public static final ModelMatcher<User> MATCHERUSER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
//                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );
}