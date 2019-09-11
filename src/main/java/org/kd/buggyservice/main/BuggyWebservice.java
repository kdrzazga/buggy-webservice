package org.kd.buggyservice.main;

import org.kd.buggyservice.common.GlobalInfo;
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
        LoggerFactory.getLogger(BuggyWebservice.class).info(GlobalInfo.getDbInfo());

        LoggerFactory.getLogger(BuggyWebservice.class).info(GlobalInfo.getSampleRequestsInfo());

        LoggerFactory.getLogger(BuggyWebservice.class).info(GlobalInfo.getSampleJsonInfo());
    }

    public static String getPort() {
        return port;
    }
}
