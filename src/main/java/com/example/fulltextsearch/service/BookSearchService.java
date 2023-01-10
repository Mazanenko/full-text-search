package com.example.fulltextsearch.service;

import com.example.fulltextsearch.dto.BookDto;

import java.util.List;

public interface BookSearchService {
    List<BookDto> search(String field, String match);
}
