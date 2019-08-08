package org.kd.buggyservice.main;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.kd.buggyservice"})
@EntityScan("org.kd.buggyservice.entities")
public class BuggyWebservice {
    private static final String port = "8081";

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(BuggyWebservice.class, args);
        writeInfo();
    }

    public static void stop() {
        context.stop();
        context.close();
    }

    private static void writeInfo() {
        LoggerFactory.getLogger(BuggyWebservice.class).info("\n\n\nSERVER STARTED");
        LoggerFactory.getLogger(BuggyWebservice.class).info("\nH2 database link: http://localhost:" + port + "/h2-console\n"
                + "Make sure h2 console is enabled in application.properties");

        LoggerFactory.getLogger(BuggyWebservice.class).info("\n\nSample requests:\n"
                + "\nPOST http://localhost:" + port + "/createAuthor/John/Kochanovsky"
                + "\nPOST http://localhost:" + port + "/createLibrary/Opole/internal"
                + "\nGET http://localhost:" + port + "/readBooks"
                + "\nGET http://localhost:" + port + "/readAuthor/1001"
                + "\nGET http://localhost:" + port + "/readLibrary/3004"
                + "\nGET http://localhost:" + port + "/readLibraries"
                + "\nPOST http://localhost:" + port + "/updateAuthor"
                + "\nDELETE http://localhost:" + port + "/deleteBook/2002\n"
                + "\nPOST http://localhost:" + port + "/stop\n"
        );

        LoggerFactory.getLogger(BuggyWebservice.class).info("\nSample JSONs for update:"
                + "\nauthor json={\"id\": 1002, \"name\": \"CK\", \"lastname\": \"Norwid\"}"
                + "\nlibrary json={\"id\": 3002, \"address\": \"Radom\"}");
    }
}
