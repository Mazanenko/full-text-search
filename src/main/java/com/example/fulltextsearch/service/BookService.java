package com.example.fulltextsearch.service;


import com.example.fulltextsearch.dto.BookDto;
import org.springframework.lang.NonNull;

import java.util.List;

public interface BookService {
    BookDto get(@NonNull Long bookId);

    List<BookDto> getAll();

    BookDto create(@NonNull BookDto bookDto);

    BookDto update(@NonNull Long bookId, @NonNull BookDto bookDto);

    void delete(@NonNull Long bookId);
}
