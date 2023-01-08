package com.example.fulltextsearch.util.mapstructMapper;


import com.example.fulltextsearch.dto.AuthorDto;
import com.example.fulltextsearch.dto.BookDto;
import com.example.fulltextsearch.model.Author;
import com.example.fulltextsearch.model.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    @Named("authorToDto")
    @Mapping(target = "books", qualifiedByName = "attachedBookToDto")
    AuthorDto authorToDto(Author author);

    @Named("authorDtoToEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "books", qualifiedByName = "attachedBookDtoToEntity")
    Author authorDtoToEntity(AuthorDto authorDto);

    @Named("updateAuthorFromDto")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateAuthorFromDto(AuthorDto authorDto, @MappingTarget Author authorForUpdate);

    @Named("attachedBookToDto")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    BookDto attachedBookToDto(Book book);

    @Named("attachedBookDtoToEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    Book attachedBookDtoToEntity(BookDto bookDto);
}
