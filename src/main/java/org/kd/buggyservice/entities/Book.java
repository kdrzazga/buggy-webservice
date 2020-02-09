package org.kd.buggyservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    private long id;
    private String title;
    private int published;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorId")// this means post_id in table indicated by Post entity is a FK
    private Author author;

    //TODO Stack Overflow with this read
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "BOOKS_IN_INTERNAL_LIBRARIES",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "library_id") }
    )
    private Set<Library> libraries = new HashSet<>();

    public Book(Author author, Integer published, String title) {

        this.author = author;
        this.published = published;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        var book = (Book) o;
        return book.id == (this.id)
                && book.title.equals(this.title)
                && book.published == (this.published);
    }
}
