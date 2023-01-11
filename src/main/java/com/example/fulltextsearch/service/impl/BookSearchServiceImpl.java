package com.example.fulltextsearch.service.impl;

import com.example.fulltextsearch.dto.BookDto;
import com.example.fulltextsearch.model.Book;
import com.example.fulltextsearch.service.BookSearchService;
import com.example.fulltextsearch.util.mapstructMapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookSearchServiceImpl implements BookSearchService {
    @PersistenceContext
    private EntityManager entityManager;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> search(String field, String match, Integer page, Integer pageSize) {
        if (page == null || pageSize == null) {
            return simpleSerach(field, match);
        }
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Book> result = searchSession.search(Book.class)
                .where(f -> f.match()
                        .field(field)
                        .matching(match))
                .fetch(page * pageSize, pageSize);
        return result.hits().stream().map(bookMapper::bookToDto).collect(Collectors.toList());
    }

    private List<BookDto> simpleSerach(String field, String match) {
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Book> result = searchSession.search(Book.class)
                .where(f -> f.match()
                        .field(field)
                        .matching(match))
                .fetchAll();
        return result.hits().stream().map(bookMapper::bookToDto).collect(Collectors.toList());
    }
}
