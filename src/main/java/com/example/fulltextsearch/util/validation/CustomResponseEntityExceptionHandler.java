package com.example.fulltextsearch.util.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        logger.debug("dev", exception);
        List<Violation> violationList = exception.getConstraintViolations()
                .stream()
                .map(v -> new Violation(getFieldName(v), v.getMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse
                        .builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .exceptionName(exception.getClass().getName())
                        .constraintViolations(violationList)
                        .build()
                );
    }

    private String getFieldName(ConstraintViolation<?> constraintViolation) {
        StringBuilder fieldName = new StringBuilder();
        for (Path.Node node : constraintViolation.getPropertyPath()) {
            fieldName.delete(0, fieldName.length());
            fieldName.append(node.getName());
        }
        return fieldName.toString();
    }
}
