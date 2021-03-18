package org.kd.buggyservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
//@Getter
//@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    protected String address;

    @ManyToMany(mappedBy = "libraries")
    private Set<Book> books = new HashSet<>();

    public Library(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
