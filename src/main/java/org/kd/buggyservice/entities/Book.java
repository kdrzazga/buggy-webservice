package org.kd.buggyservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    private long id;
    private String title;
    private int published;

    //@ManyToOne
    private Author authorId;

    public Book(Author author, Integer published, String title) {
        this.authorId = author;
        this.published = published;
        this.title = title;
    }
}
