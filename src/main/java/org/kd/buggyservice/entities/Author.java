package org.kd.buggyservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Author implements Serializable {

    public Author(String name, String lastname){
        this.name = name;
        this.lastname = lastname;
    }

    @Id
    private long id;
    private String name;
    private String lastname;
}
