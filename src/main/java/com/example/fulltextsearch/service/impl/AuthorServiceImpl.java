package com.example.fulltextsearch.service.impl;


import com.example.fulltextsearch.dto.AuthorDto;
import com.example.fulltextsearch.model.Author;
import com.example.fulltextsearch.repository.AuthorRepository;
import com.example.fulltextsearch.service.AuthorService;
import com.example.fulltextsearch.util.mapstructMapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    @Override
    public AuthorDto get(@NonNull Long authorId) {
        return authorMapper.authorToDto(getFromDb(authorId));
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream().map(authorMapper::authorToDto).toList();
    }

    @Override
    public AuthorDto create(@NonNull AuthorDto authorDto) {
        Author author = authorMapper.authorDtoToEntity(authorDto);
        return authorMapper.authorToDto(authorRepository.save(author));
    }

    @Override
    public AuthorDto update(@NonNull Long authorId, @NonNull AuthorDto authorDto) {
        Author author = getFromDb(authorId);
        authorMapper.updateAuthorFromDto(authorDto, author);
        return authorMapper.authorToDto(authorRepository.save(author));
    }

    @Override
    @Transactional
    public void delete(@NonNull Long authorId) {
        authorRepository.delete(authorRepository.getReferenceById(authorId));
    }

    private Author getFromDb(@NonNull Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "В бд нет автора с id: " + authorId));
    }
}
