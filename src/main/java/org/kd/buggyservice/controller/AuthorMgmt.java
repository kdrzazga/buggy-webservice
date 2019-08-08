package org.kd.buggyservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.buggyservice.dao.AuthorRepo;
import org.kd.buggyservice.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AuthorMgmt {

    @Autowired
    private AuthorRepo authorRepo;

    @PostMapping(path = "/createAuthor/{newName}/{newLastName}")
    public ResponseEntity<Long> create(@PathVariable String newName, @PathVariable String newLastName) {

        try {
            Long id = authorRepo.create(newName, newLastName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(id);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/readAuthor/{id}")
    public ResponseEntity<Author> read(@PathVariable Long id) {
        //TODO: performance error on purpose - long waiting for reading author

        try {
            var author = authorRepo.read(id);
            Thread.sleep(Math.round(7000 + 18000 * Math.random()));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(author);
        } catch (InterruptedException e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("message", e.getMessage())
                    .build();
        }
    }

    @GetMapping(path = "/readAuthors")
    public ResponseEntity<List<Author>> readAll() {
        var authors = authorRepo.readAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authors);
    }


    @PostMapping(path = "/updateAuthor", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> update(@RequestBody String authorJson) {

        try {
            var objectMapper = new ObjectMapper();
            var author = objectMapper.readValue(authorJson, Author.class);
            var responseBody = authorRepo.updateAuthorName(author.getId(), author.getName(), author.getLastname());
            var authorAsString = objectMapper.writeValueAsString(responseBody);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authorAsString);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("message", e.getMessage())
                    .body("");
        }
    }

    @DeleteMapping(path = "deleteAuthor/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return (authorRepo.delete(id))
                ? ResponseEntity
                .status(HttpStatus.OK)
                .header("message", "Author " + id + " deleted.")
                .build()
                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("message", "Couldn't delete author with id = " + id)
                .build();
    }

}
