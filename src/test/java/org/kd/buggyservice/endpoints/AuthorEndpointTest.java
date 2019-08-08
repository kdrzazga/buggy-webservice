package org.kd.buggyservice.endpoints;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.common.RestUtility;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorEndpointTest {

    @Autowired
    private RestUtility restUtility;

    @Value("${server.port}")
    private String port;

    @BeforeClass
    public static void setUp(){
        SpringApplication.run(BuggyWebservice.class, new String[0]);
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadAuthors() {
        var endpoint = "/readAuthors";
        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, "", "http://localhost:" + port + endpoint, MediaType.TEXT_PLAIN.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @AfterClass
    public void tearDown(){}

}
