package org.kd.buggyservice.common.repo;

import java.util.Map;

public enum Roles {
    ADMIN, USER;

    public String getLogin(){
        Map<Roles, String> loginMap = Map.of(Roles.ADMIN, "admin",
                Roles.USER, "user");

        return loginMap.get(this);
    }

    public String getPassword(){
        Map<Roles, String> passwordMap = Map.of(Roles.ADMIN, "admin",
                Roles.USER, "user");

        return passwordMap.get(this);
    }
}
