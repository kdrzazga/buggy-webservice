package org.kd.buggyservice.dao;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.entities.Book;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRepoUnitTest {

    @Autowired
    private BookRepo bookRepo;

    @Test
    public void testCreate() {
        var initialId = Long.valueOf(1981);
        var newBook = new Book(initialId, "Fake Book", 1, null);

        Long newId = bookRepo.create(newBook);

        assertNotNull(newId);
        assertNotEquals(newId, initialId);
    }

    @Test
    public void testRead() {
        var id = 2001L;
        var expectedTitle = "Pan Tadeusz";
        var book1 = bookRepo.read(id);

        assertEquals("Check if DB script was changed", expectedTitle, book1.getTitle());
    }

    @Test
    public void testReadAllBooks() {
        var books = bookRepo.readAll();

        assertNotNull(books);
        assertThat(books, hasSize(Matchers.greaterThan(0)));
        assertEquals(24, books.size());
    }

    @Test
    public void testUpdate() {
        //update is purposely wrong
        Assert.fail("NOt implemented");
    }
}
