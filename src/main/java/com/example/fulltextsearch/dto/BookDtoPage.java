package com.example.fulltextsearch.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookDtoPage {
    private String filter;
    private String sort;
    private int page;
    private int elementsOnPage;
    private long totalAmount;
    private int totalPages;
    private List<BookDto> books;
}
