package org.kd.buggyservice.common.repo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.kd.buggyservice.common.repo.Roles.ADMIN;
import static org.kd.buggyservice.common.repo.Roles.USER;

public final class BWLoginRepo {
    public UserDetails getAdmin() {
        return User.withDefaultPasswordEncoder()
                .username(ADMIN.getLogin())
                .password(ADMIN.getPassword())
                .roles(ADMIN.name())
                .build();
    }

    public UserDetails getUser() {
        return User.withDefaultPasswordEncoder()
                .username(USER.getLogin())
                .password(USER.getPassword())
                .roles(USER.name())
                .build();
    }
}
