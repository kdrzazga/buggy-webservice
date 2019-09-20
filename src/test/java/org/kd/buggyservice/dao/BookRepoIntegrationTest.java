package org.kd.buggyservice.dao;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kd.buggyservice.entities.Book;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRepoIntegrationTest {

    @Autowired
    private BookRepo bookRepo;

    @Test
    @Order(value = 1)
    public void testCreate() {
        var initialId = Long.valueOf(1981);
        var newBook = new Book(initialId, "Fake Book", 1, 0);

        Long newId = bookRepo.create(newBook);

        var readBook = bookRepo.read(newId);
        assertNotNull(newId);
        assertEquals(newId.longValue(), readBook.getId());
        //bookRepo.delete(initialId); -> purposely delete doesn't work
    }

    @Test
    @Order(value = 2)
    public void testRead() {
        var id = 2001L;
        var expectedTitle = "Pan Tadeusz";
        var book1 = bookRepo.read(id);

        assertEquals("Check if DB script was changed", expectedTitle, book1.getTitle());
    }

    @Test
    @Order(value = 3)
    public void testReadAllBooks() {
        var books = bookRepo.readAll();

        assertNotNull(books);

        //test depends on testCreate() - it shouldn't be like that,
        // so if testCreate() is executed before testReadAllBooks() we have 26 books, otherwise 25
        assertThat(books, hasSize(Matchers.greaterThan(24)));
        assertThat(books, hasSize(Matchers.lessThan(27)));
    }

    @Test
    @Order(value =4)
    public void testUpdate() {
        var book = bookRepo.read(2001);
        var newTitle = "fake";
        book.setTitle(newTitle);

        var updatedBook = bookRepo.update(book.getId(), book.getTitle(), book.getPublished());

        var storedBook = bookRepo.read(2001);

        assertNotEquals(book.getTitle(), storedBook.getTitle()); //update is purposely wrong
        assertEquals(newTitle, updatedBook.getTitle()); //but new record should be ok
    }

    @Test
    @Order(value =5)
    public void testDelete(){
        long id = 2001;
        var book = bookRepo.read(id);

        bookRepo.delete(book.getId());

        var deletedBook = bookRepo.read(id);
        assertEquals(book, deletedBook);// delete has error on purpose and nothing is deleted
    }
}
