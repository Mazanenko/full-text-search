package com.example.fulltextsearch.controller;


import com.example.fulltextsearch.dto.BookDto;
import com.example.fulltextsearch.service.BookService;
import com.example.fulltextsearch.util.validation.profile.OnCreate;
import com.example.fulltextsearch.util.validation.profile.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books")
@Validated
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable("id") @Positive Long id) {
        return ResponseEntity.ok(bookService.get(id));
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping()
    @Validated(OnCreate.class)
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(dto));
    }

    @PatchMapping("/{id}")
    @Validated(OnUpdate.class)
    public ResponseEntity<BookDto> update(@PathVariable("id") @Positive Long id, @RequestBody @Valid BookDto dto) {
        return ResponseEntity.ok(bookService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") @Positive Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
