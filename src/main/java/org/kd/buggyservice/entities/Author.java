package org.kd.buggyservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Author implements Serializable {

    public Author(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Id
    private long id;
    private String name;
    private String lastname;

  /* //Uncomment to create bi-directinal relation
    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>();
  */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Author)) return false;

        var author = (Author) o;
        return author.id == (this.id)
                && author.name.equals(this.name)
                && author.lastname.equals(this.lastname);
    }
}
