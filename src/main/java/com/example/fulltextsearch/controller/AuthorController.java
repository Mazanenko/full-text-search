package com.example.fulltextsearch.controller;


import com.example.fulltextsearch.dto.AuthorDto;
import com.example.fulltextsearch.service.AuthorService;
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
@RequestMapping(value = "/authors")
@Validated
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@Positive @PathVariable Long id) {
        return ResponseEntity.ok(authorService.get(id));
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping()
    @Validated(OnCreate.class)
    public ResponseEntity<AuthorDto> create(@RequestBody @Valid AuthorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(dto));
    }

    @PatchMapping("/{id}")
    @Validated(OnUpdate.class)
    public ResponseEntity<AuthorDto> update(@PathVariable("id") @Positive Long id, @RequestBody @Valid AuthorDto dto) {
        return ResponseEntity.ok(authorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> delete(@PathVariable("id") @Positive Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
