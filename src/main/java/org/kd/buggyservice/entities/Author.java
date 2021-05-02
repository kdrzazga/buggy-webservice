package org.kd.buggyservice.entities;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
//@NoArgsConstructor
//@Getter
@Setter
public class Author implements Serializable {

    public Author() {
    }

    public Author(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Id
    private long id;
    private String name;
    private String lastname;

    public long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Author)) return false;

        var author = (Author) o;
        return author.id == (this.id)
                && author.name.equals(this.name)
                && author.lastname.equals(this.lastname);
    }
}
