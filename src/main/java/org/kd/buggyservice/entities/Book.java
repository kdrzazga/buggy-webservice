package org.kd.buggyservice.entities;

import lombok.*;
import javax.persistence.*;
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

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId")// this means post_id in table indicated by Post entity is a FK
    private Author author;
    */
    private long authorId;

    public Book(long author, Integer published, String title) {

        this.authorId = author;
        this.published = published;
        this.title = title;
    }
}
