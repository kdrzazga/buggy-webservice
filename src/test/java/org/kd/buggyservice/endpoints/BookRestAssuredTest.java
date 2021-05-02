package org.kd.buggyservice.endpoints;

import io.restassured.common.mapper.TypeRef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.entities.Book;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.kd.buggyservice.common.repo.Roles.ADMIN;
import static org.kd.buggyservice.common.repo.Roles.USER;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookRestAssuredTest {
    private final String port = BuggyWebservice.getPort();

    @Test
    public void testReadBooks() {
        var endpoint = "/readBooks";

        List<Book> response = given().auth()
                .basic(USER.getLogin(), USER.getPassword())
                .when()
                .get("http://localhost:" + port + endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .as(new TypeRef<ArrayList<Book>>() {
                });

        assertNotNull(response);
        assertThat(response, hasSize(greaterThan(0)));
        assertNotNull(response.get(0).getTitle());
        assertNotNull(response.get(0).getAuthor());
    }

    @Test
    public void testDeleteBook() {
        var endpoint = "/deleteBook/2001";

        given().auth()
                .basic(ADMIN.getLogin(), ADMIN.getPassword())
                .when()
                .delete("http://localhost:" + port + endpoint)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .asString()
                .contains("deleted");
    }
}
