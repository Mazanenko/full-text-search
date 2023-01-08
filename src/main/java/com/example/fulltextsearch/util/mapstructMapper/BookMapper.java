package com.example.fulltextsearch.util.mapstructMapper;


import com.example.fulltextsearch.dto.AuthorDto;
import com.example.fulltextsearch.dto.BookDto;
import com.example.fulltextsearch.model.Author;
import com.example.fulltextsearch.model.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Named("bookToDto")
    @Mapping(target = "author", qualifiedByName = "attachedAuthorToDto")
    BookDto bookToDto(Book book);

    @Named("bookDtoToEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "author", qualifiedByName = "attachedAuthorDtoToEntity")
    Book bookDtoToEntity(BookDto bookDto);

    @Named("updateBookFromDto")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "author", qualifiedByName = "attachedAuthorDtoToEntity")
    void updateBookFromDto(BookDto bookDto, @MappingTarget Book bookForUpdate);

    @Named("attachedAuthorDtoToEntity")
    @Mapping(target = "books", ignore = true)
    Author attachedAuthorDtoToEntity(AuthorDto authorDto);

    @Named("attachedAuthorToDto")
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    AuthorDto attachedAuthorToDto(Author author);
}
