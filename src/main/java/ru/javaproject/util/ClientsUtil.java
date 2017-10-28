package ru.javaproject.util;

import ru.javaproject.model.Client;
import ru.javaproject.model.Role;

import java.util.Arrays;
import java.util.List;

public class ClientsUtil {
    public static final List<Client> CLIENTS = Arrays.asList( new Client(1, "Алексей", "email1", "password", Role.ROLE_REPRESENTATIVE),
            new Client(2, "userName2", "email2", "password", Role.ROLE_USER),
            new Client(3, "userName3", "email3", "password", Role.ROLE_USER),
            new Client(4, "userName4", "email4", "password", Role.ROLE_USER));
}
