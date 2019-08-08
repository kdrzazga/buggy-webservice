package org.kd.buggyservice.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BuggyserviceConfig {

    @Bean
    public RestUtility restUtility() {
        return new RestUtility();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
