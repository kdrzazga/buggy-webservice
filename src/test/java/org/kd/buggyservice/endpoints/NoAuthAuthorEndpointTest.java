package org.kd.buggyservice.endpoints;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.common.RestUtility;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class NoAuthAuthorEndpointTest {

    @Autowired
    private RestUtility restUtility;

    private final String port = BuggyWebservice.getPort();

    @Test(expected = ResourceAccessException.class)//requires authorization
    public void testCreateAuthor() {
        var endpoint = "/createAuthor/John/Kochanovsky";
        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.POST, ""
                , "http://localhost:" + port + endpoint, MediaType.TEXT_PLAIN.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = ResourceAccessException.class)//requires authorization
    public void testReadAuthor() {
        var endpoint = "/readAuthor/1002";
        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, ""
                , "http://localhost:" + port + endpoint, MediaType.TEXT_PLAIN.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = ResourceAccessException.class)//requires authorization
    public void testReadAuthors() {
        var endpoint = "/readAuthors";
        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, ""
                , "http://localhost:" + port + endpoint, MediaType.TEXT_PLAIN.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
