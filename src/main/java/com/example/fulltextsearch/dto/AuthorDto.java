package com.example.fulltextsearch.dto;


import com.example.fulltextsearch.util.validation.profile.OnCreate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDto {
    private final Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Null(message = "значение устанавливается системой")
    private final Instant createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Null(message = "значение устанавливается системой")
    private final Instant modifiedAt;
    @NotBlank(groups = OnCreate.class, message = "поле не может быть пустым")
    private final String firstName;
    @NotBlank(groups = OnCreate.class, message = "поле не может быть пустым")
    private final String middleName;
    @NotBlank(groups = OnCreate.class, message = "поле не может быть пустым")
    private final String lastName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Set<BookDto> books;
}
