package com.example.fulltextsearch.dto;

import com.example.fulltextsearch.util.validation.profile.OnCreate;
import com.example.fulltextsearch.util.validation.profile.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Null(message = "значение устанавливается системой")
    private final Instant createdAt;
    @Null(message = "значение устанавливается системой")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Instant modifiedAt;
    @NotBlank(groups = OnCreate.class, message = "поле не может быть пустым")
    private final String name;
    @NotBlank(groups = OnCreate.class, message = "поле не может быть пустым")
    private final String genre;
    @NotBlank(groups = OnCreate.class, message = "поле не может быть пустым")
    private final String description;
    @Positive(groups = {OnCreate.class, OnUpdate.class}, message = "значение должно быть положительным")
    @NotNull(groups = OnCreate.class)
    private final BigDecimal price;
    @NotNull(groups = OnCreate.class)
    @Valid
    private final AuthorDto author;
}
