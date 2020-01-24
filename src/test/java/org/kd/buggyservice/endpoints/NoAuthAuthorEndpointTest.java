package org.kd.buggyservice.endpoints;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.common.RestUtility;
import org.kd.buggyservice.common.repo.BWLoginRepo;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Import(BWLoginRepo.class)
public class NoAuthAuthorEndpointTest {

    @Autowired
    private RestUtility restUtility;

    private final String port = BuggyWebservice.getPort();
    private final String probableFailureCause = "Please check if application isn't running while running tests";

    @BeforeClass
    public static void startWebsevice() {
        //BuggyWebservice.main(new String[0]);
    }

    @Test//(expected = ResourceAccessException.class)//requires authorization
    @WithUserDetails(value = "admin")
    public void testCreateAuthor() {
        var endpoint = "/createAuthor/John/Kochanovsky";
        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.POST, ""
                , "http://localhost:" + port + endpoint, MediaType.TEXT_PLAIN.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        var numberRegex = "";
        //assertThat(response.getBody(), matchesPattern("[0-9]+"));
        response.getBody().matches(numberRegex);
    }

    @Test//(expected = ResourceAccessException.class)//requires authorization
    @WithUserDetails(value = "user")
    public void testReadAuthor() {
        var endpoint = "/readAuthor/1002";
        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, ""
                , "http://localhost:" + port + endpoint, MediaType.TEXT_PLAIN.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody(), Matchers.stringContainsInOrder(List.of("{", "id", "name", "lastname", "}")));
    }

    @Test//(expected = ResourceAccessException.class)//requires authorization
    //@WithUserDetails(value="user")
    public void testReadAuthors() {
        var endpoint = "/readAuthors";
        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, ""
                , "http://localhost:" + port + endpoint, MediaType.TEXT_PLAIN.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody(), Matchers.stringContainsInOrder(List.of("[", "{", "id", "name", "lastname", "}", "]")));
    }

}
