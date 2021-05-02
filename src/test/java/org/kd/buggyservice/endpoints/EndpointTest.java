package org.kd.buggyservice.endpoints;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.common.repo.BWLoginRepo;
import org.kd.buggyservice.entities.Author;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Import(BWLoginRepo.class)
public class EndpointTest {

    @Autowired
    private RestTemplate restTemplate;

    private final String port = BuggyWebservice.getPort();
    private final String probableFailureCause = "Please check if application is running while executing tests";

    @Test
    public void testCreateAuthor() {
        var endpoint = "/createAuthor/John/Kochanovsky";

        restTemplate.getInterceptors()
                .add(new BasicAuthorizationInterceptor("admin", "admin"));

        ResponseEntity<Integer> response =  restTemplate
                .exchange("http://localhost:" + port + endpoint,
                        HttpMethod.POST, null
                        , Integer.class);

        assertEquals(probableFailureCause, HttpStatus.OK, response.getStatusCode());
        assertThat(probableFailureCause, response.getBody(), Matchers.greaterThanOrEqualTo(0));
    }

    @Test
    public void testReadAuthor() {
        var endpoint = "/readAuthor/1002";
        setCredentials("admin", "admin");

        ResponseEntity<Author> response =
                restTemplate
                        .exchange("http://localhost:" + port + endpoint,
                                HttpMethod.GET, null
                                , Author.class);

        assertEquals(probableFailureCause, HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getName());
        assertThat(response.getBody().getLastname().length(), Matchers.greaterThan(0));
    }

    @Test
    public void testReadAuthors() {
        var endpoint = "/readAuthors";
        setCredentials("admin", "admin");
       // var restTemplate = restUtility.getRestTemplate();

        ResponseEntity<List> response = restTemplate
                .exchange("http://localhost:" + port + endpoint,
                        HttpMethod.GET, null
                        , List.class);

        assertEquals(probableFailureCause, HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody().size(), Matchers.greaterThan(0));
    }

    private void setCredentials(String user, String password) {
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(user, password));
    }
}
