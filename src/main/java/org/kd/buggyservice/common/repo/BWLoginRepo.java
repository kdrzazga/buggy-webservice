package org.kd.buggyservice.common.repo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.kd.buggyservice.common.repo.Roles.ADMIN;
import static org.kd.buggyservice.common.repo.Roles.USER;

public final class BWLoginRepo {

    @Bean
    public UserDetails admin() {
        return User.withUsername(ADMIN.getLogin())
                .password(ADMIN.getPassword())
                .roles(ADMIN.name())
                .build();
    }

    @Bean
    public UserDetails user() {
        return User.withUsername(USER.getLogin())
                .password(USER.getPassword())
                .roles(USER.name())
                .build();
    }
}
