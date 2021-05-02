package org.kd.buggyservice.endpoints;

import io.restassured.common.mapper.TypeRef;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.entities.Author;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.kd.buggyservice.common.repo.Roles.USER;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorRestAssuredTest {
    private final String port = BuggyWebservice.getPort();

    @Test
    public void testCreateAuthor() {
        var endpoint = "/createAuthor/John/Kochanovsky";

        Long response = given().auth()
                .basic(USER.getLogin(), "")
                .when()
                .post("http://localhost:" + port + endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        assertNotNull(response);
    }

    @Test
    public void testReadAuthor() {
        var endpoint = "/readAuthor/1002";

        Author response = given().auth()
                .basic(USER.getLogin(), USER.getPassword())
                .when()
                .get("http://localhost:" + port + endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        assertNotNull(response);
        assertThat(response.getLastname().length(), Matchers.greaterThan(0));
        assertThat(response.getId(), Matchers.greaterThanOrEqualTo(1000L));
    }

    @Test
    public void testReadAuthors() {
        var endpoint = "/readAuthors";

        List<Author> response = given().auth()
                .basic(USER.getLogin(), USER.getPassword())
                .when()
                .get("http://localhost:" + port + endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .as(new TypeRef<ArrayList<Author>>() {
                });

        assertNotNull(response);
        assertThat(response, hasSize(greaterThan(0)));
        assertNotNull(response.get(0).getName());
        assertNotNull(response.get(0).getLastname());
    }

}
