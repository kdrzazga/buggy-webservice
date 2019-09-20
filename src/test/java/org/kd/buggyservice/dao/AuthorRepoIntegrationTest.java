package org.kd.buggyservice.dao;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorRepoIntegrationTest {

    @Autowired
    private AuthorRepo authorRepo;

    @Test
    @Order(value = 1)
    public void test_Read() {
        var id = 1001L;
        var expectedLastName = "Mickiewicz";
        var author = authorRepo.read(id);

        assertEquals("Check if DB script was changed", expectedLastName, author.getLastname());
    }

    @Test
    @Order(value = 2)
    public void test_ReadAllAuthors() {
        var authors = authorRepo.readAll();

        assertNotNull(authors);
        assertThat(authors, hasSize(Matchers.greaterThan(0)));

        var authorNames = new ArrayList<String>();

        authors.forEach(author -> authorNames.add(author.getLastname()));

        assertThat(authorNames, IsCollectionContaining.hasItems("Mickiewicz"));
    }

    @Test
    @Order(value = 3)
    public void testCreate() {

        var authorName = "Roger";
        var authorLastName = "Zelazny";
        var id = authorRepo.create(authorName, authorLastName);
        assertNotNull(id);
        var stored = authorRepo.read(id);
        assertNotNull(stored);
        assertEquals(authorName, stored.getName());
        assertEquals(authorLastName, stored.getLastname());
    }

    @Test
    @Order(value = 4)
    public void test_Update() {
        var id = 1002L;
        var newName = "Test";
        var newLastName = "Writer";
        authorRepo.update(id, newName, newLastName);

        var updated = authorRepo.read(id);

        assertEquals(newName, updated.getName());
        assertEquals(newLastName, updated.getLastname());
    }

    @Test(expected = Exception.class)
    //@Ignore
    @Order(value = 5)
    public void testDelete() {
        authorRepo.delete(1002L);
        //purposely the table was dropped, so nothing can be read
        authorRepo.readAll();
    }
}
