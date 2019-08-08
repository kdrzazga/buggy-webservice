package org.kd.buggyservice.dao;

import org.h2.jdbc.JdbcSQLException;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.entities.Book;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorRepoUnitTest {

    @Autowired
    private AuthorRepo authorRepo;

    @Test
    public void testRead() {
        var id = 1001L;
        var expectedLastName = "Mickiewicz";
        var book1 = authorRepo.read(id);

        assertEquals("Check if DB script was changed", expectedLastName, book1.getLastname());
    }

    @Test
    public void testReadAllAuthors() {
        var authors = authorRepo.readAll();

        assertNotNull(authors);
        assertThat(authors, hasSize(Matchers.greaterThan(0)));

        var authorNames = new ArrayList<String>();

        authors.forEach(author -> authorNames.add(author.getLastname()));

        assertThat(authorNames, IsCollectionContaining.hasItems("Mickiewicz"));
    }

    @Test
    public void testCreate() {

        var authorName = "Roger";
        var authorLastName = "Zelazny";
        var id = authorRepo.create(authorName, authorLastName);
        assertNotNull(id);
        var stored  = authorRepo.read(id);
        assertNotNull(stored);
        assertEquals(authorName, stored.getName());
        assertEquals(authorLastName, stored.getLastname());
    }

    @Test
    public void testUpdate() {
        var id = 1001L;
        var newName = "Test";
        var newLastName = "Writer";
        authorRepo.updateAuthorName(id, newName, newLastName);

        var updated = authorRepo.read(id);

        assertEquals(newName, updated.getName());
        assertEquals(newLastName, updated.getLastname());
    }

    @Test(expected = Exception.class)
    public void testDelete() {
        authorRepo.delete(1002L);
        //purposely the table was dropped, so nothing can be read
        authorRepo.readAll();
    }
}
