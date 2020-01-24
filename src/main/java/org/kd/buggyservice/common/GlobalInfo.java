package org.kd.buggyservice.common;

import org.kd.buggyservice.main.BuggyWebservice;

import static org.kd.buggyservice.common.repo.Roles.ADMIN;
import static org.kd.buggyservice.common.repo.Roles.USER;

public final class GlobalInfo {

    private static final String host = "http://localhost:";

    public static String getDbInfo() {

        return "\nH2 database link: " + host + BuggyWebservice.getPort() + "/h2-console\n"
                + "Credentials: user sa, (no password)\n"
                + "JDBC URL = jdbc:h2:mem:testdb\n";
    }

    public static String getAppCredentials() {
        return "\nApplication login: " + ADMIN.getLogin() + ", pass: " + ADMIN.getPassword()
                + "\nApplication login: " + USER.getLogin() + ", pass: " + USER.getPassword();
    }

    public static String getSampleRequestsInfo() {
        var port = BuggyWebservice.getPort();

        return "\nOpen SWAGGER for all available set of request, login as admin\n" +
                host + port + "/swagger-ui.html\n";
    }

    public static String getSampleJsonInfo() {
        return "\nSample JSONs for update:"
                + "\nauthor json={\"id\": 1002, \"name\": \"CK\", \"lastname\": \"Norwid\"}"
                + "\nbook json={\"id\": 2002, \"title\": \"Ania z Zielonego Wzgorza\", \"published\": \"2019\", \"author\": \"1003\"}"
                + "\nlibrary json={\"id\": 3002, \"address\": \"Radom\"}";
    }
}
