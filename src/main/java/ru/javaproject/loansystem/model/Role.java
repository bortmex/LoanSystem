package ru.javaproject.loansystem.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_PARTNER,
    ROLE_REPRESENTATIVE,
    ROLE_SUPERUSER;

    @Override
    public String getAuthority() {
        return name();
    }
}
