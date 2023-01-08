package com.example.fulltextsearch.service.impl;


import com.example.fulltextsearch.dto.BookDto;
import com.example.fulltextsearch.model.Book;
import com.example.fulltextsearch.repository.BookRepository;
import com.example.fulltextsearch.service.BookService;
import com.example.fulltextsearch.util.mapstructMapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto get(@NonNull Long bookId) {
        return bookMapper.bookToDto(getFromDb(bookId));
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::bookToDto).toList();
    }

    @Override
    @Transactional
    public BookDto create(@NonNull BookDto bookDto) {
        Book book = bookMapper.bookDtoToEntity(bookDto);
        return bookMapper.bookToDto(bookRepository.save(book));
    }

    @Override
    public BookDto update(@NonNull Long bookId, @NonNull BookDto bookDto) {
        Book book = getFromDb(bookId);
        bookMapper.updateBookFromDto(bookDto, book);
        return bookMapper.bookToDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void delete(@NonNull Long bookId) {
        bookRepository.delete(bookRepository.getReferenceById(bookId));
    }

    private Book getFromDb(@NonNull Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "В бд нет книги с id: " + bookId));
    }
}
