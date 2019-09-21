package org.kd.buggyservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.buggyservice.common.ExceptionFormatter;
import org.kd.buggyservice.dao.AuthorRepo;
import org.kd.buggyservice.dao.BookRepo;
import org.kd.buggyservice.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookMgmt {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @PostMapping(path = "/createBook/{authorId}/{title}/{published_year}")
    public ResponseEntity<Long> create(@PathVariable Long authorId, @PathVariable String title, @PathVariable Long published_year) {
        var author = authorRepo.read(authorId);
        //var newBook = new Book(author.getId(), published_year.intValue(), title);
        var newBook = new Book(author, published_year.intValue(), title);
        var newId = bookRepo.create(newBook);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newId);
    }

    @GetMapping(path = "/readBook/{id}")
    public ResponseEntity<Book> read(@PathVariable Long id) {
        var book = bookRepo.read(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }

    @GetMapping(path = "/readBooks")
    public ResponseEntity<List<Book>> readAll() {
        var book = bookRepo.readAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(book);
    }

    @PutMapping(path = "/updateBook", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> update(@RequestBody String bookJson) {

        try {
            var objectMapper = new ObjectMapper();
            var book = objectMapper.readValue(bookJson, Book.class);
            var responseBody = bookRepo.update(book.getId(), book.getTitle(), book.getPublished());
            String bookAsString = objectMapper.writeValueAsString(responseBody);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookAsString);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("message", ExceptionFormatter.fetchStacktrace(e))
                    .body("");
        }
    }

    @DeleteMapping(path = "/deleteBook/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        return (bookRepo.delete(id))
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Book " + id + " deleted.")

                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Couldn't delete book with id = " + id);
    }

}
