package com.example.fulltextsearch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "authors")
@Indexed(index = "idx_author")
public class Author extends BaseEntity {
    @Column(name = "first_name")
    @FullTextField(name = "firstName")
    private String firstName;

    @Column(name = "middle_name")
    @FullTextField(name = "middleName")
    private String middleName;

    @Column(name = "last_name")
    @FullTextField(name = "lastName")
    private String lastName;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude

    private Set<Book> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author author = (Author) o;
        return getId() != null && Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
