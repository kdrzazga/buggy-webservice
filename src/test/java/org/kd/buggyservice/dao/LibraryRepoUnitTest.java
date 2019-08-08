package org.kd.buggyservice.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.buggyservice.main.BuggyWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@SpringBootTest(classes = {BuggyWebservice.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LibraryRepoUnitTest {

    @Autowired
    LibraryRepo libraryRepo;

    @Test
    public void testRead(){
        var libraryId = 3001L;
        var library3001 = libraryRepo.read(libraryId);

        assertNotNull(library3001);
        assertEquals(Long.valueOf(libraryId), library3001.getId());
    }

    @Test
    public void testUpdate(){
        var libraryId = 3001L;
        var newAddress = "FAKE ADDRESS";
        libraryRepo.update(libraryId, newAddress);

        var library3001 = libraryRepo.read(libraryId);

        assertNotNull(library3001);
        assertEquals(newAddress, library3001.getAddress());
    }
}
