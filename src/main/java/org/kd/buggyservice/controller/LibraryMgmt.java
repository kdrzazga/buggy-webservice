package org.kd.buggyservice.controller;

import org.kd.buggyservice.dao.LibraryRepo;
import org.kd.buggyservice.entities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryMgmt {

    @Autowired
    private LibraryRepo libraryRepo;

    @PostMapping(path = "/createLibrary/{address}/{type}")
    public ResponseEntity<Long> create(@PathVariable String address, @PathVariable String type) {
        //TODO: Functional error - creates only INTERNAL libraries, type is ignored
        var libId = libraryRepo.create(address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(libId);
    }

    @GetMapping(path = "/readLibrary/{id}")
    public ResponseEntity<Library> read(@PathVariable long id) {
        //TODO: Functional error on purpose - only single INTERNAL library can be read this way,
        // while readAll reads all internal and external libs
        var internalLib = libraryRepo.read(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(internalLib);
    }

    @GetMapping(path = "/readLibraries")
    public ResponseEntity<List<Library>> readAll() {
        var libs = libraryRepo.readAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(libs);
    }

    @PutMapping(path = "/updateLibrary", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Library> update(@RequestBody Library library) {
        var responseBody = libraryRepo.update(library.getId(), library.getAddress());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @DeleteMapping(path = "/deleteLibrary/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        return (libraryRepo.delete(id))
                ? ResponseEntity
                .status(HttpStatus.OK)
                .header("message", "Library " + id + " deleted.")
                .build()
                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("message", "Couldn't delete Library with id = " + id)
                .build();
    }
}
