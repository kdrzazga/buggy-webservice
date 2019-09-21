package org.kd.buggyservice.dao;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

//@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LibraryRepoIntegrationTest {

    @Autowired
    LibraryRepo libraryRepo;

    @Test
    @Order(value = 3)
    public void testCreate() {
        var newLibLocation = "Fake Library";

        Long newId = libraryRepo.create(newLibLocation);

        var readLib = libraryRepo.read(newId);
        assertNotNull(newId);
        assertEquals(newId, readLib.getId());
        //created record should be deleted here, but this functionality is wrong
    }

    @Test
    @Order(value = 1)
    public void testRead() {
        var libraryId = 3001L;
        var library3001 = libraryRepo.read(libraryId);

        assertNotNull(library3001);
        assertEquals(Long.valueOf(libraryId), library3001.getId());
    }

    @Test
    @Order(value = 2)
    public void testReadAll() {
        var libraryId = 3001L;
        var libraries = libraryRepo.readAll();

        assertNotNull(libraries);
        assertThat(libraries, hasSize(Matchers.greaterThan(4)));
    }

    @Test
    @Order(value = 4)
    public void testUpdate() {
        var libraryId = 3001L;
        var newAddress = "FAKE ADDRESS";
        libraryRepo.update(libraryId, newAddress);

        var library3001 = libraryRepo.read(libraryId);

        assertNotNull(library3001);
        assertEquals(newAddress, library3001.getAddress());
    }

    @Test
    @Order(5)
    public void testDelete() {
        long id = 3001;
        var book = libraryRepo.read(id);

        libraryRepo.delete(book.getId());

        var deletedBook = libraryRepo.read(id);
        assertEquals(book.getId(), deletedBook.getId());
        assertNotEquals(book.getAddress(), deletedBook.getAddress());// delete has error on purpose and only
        // address is resetted
    }
}
