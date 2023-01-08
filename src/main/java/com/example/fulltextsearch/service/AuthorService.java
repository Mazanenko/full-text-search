package com.example.fulltextsearch.service;


import com.example.fulltextsearch.dto.AuthorDto;
import org.springframework.lang.NonNull;

import java.util.List;

public interface AuthorService {
    AuthorDto get(@NonNull Long authorId);

    List<AuthorDto> getAll();

    AuthorDto create(@NonNull AuthorDto authorDto);

    AuthorDto update(@NonNull Long authorId, @NonNull AuthorDto authorDto);

    void delete(@NonNull Long authorId);
}
