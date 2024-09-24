package dev.shirleano.classmaster_backend.dto.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDto(
        HttpStatus status,
        String message
) {
}
