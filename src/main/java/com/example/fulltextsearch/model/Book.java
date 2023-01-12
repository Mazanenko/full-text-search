package com.example.fulltextsearch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.engine.backend.analysis.AnalyzerNames;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@DynamicUpdate
@Entity
@Table(name = "books")
@Indexed(index = "idx_book")
@NamedEntityGraph(name = "withAuthor", attributeNodes = @NamedAttributeNode("author"))
public class Book extends BaseEntity {

    @Column(name = "name")
    @FullTextField(name = "name")
    private String name;

    @Column(name = "genre")
    @KeywordField(name = "genre", sortable = Sortable.YES)
    private String genre;

    @Column(name = "description")
    @FullTextField(name = "description", analyzer = AnalyzerNames.STOP)
    private String description;

    @Column(name = "price")
    @GenericField(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    @IndexedEmbedded(includeDepth = 1)
    private Author author;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;

        if (!name.equals(book.name)) return false;
        return genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }
}
