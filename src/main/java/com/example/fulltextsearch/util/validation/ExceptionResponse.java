package com.example.fulltextsearch.util.validation;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
public class ExceptionResponse {
    @Nullable
    HttpStatus httpStatus;
    @Nullable
    String message;
    @Nullable
    String exceptionName;

    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Violation> constraintViolations;
}
