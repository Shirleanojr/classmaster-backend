package dev.shirleano.classmaster_backend.infra.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import dev.shirleano.classmaster_backend.dto.exception.ExceptionDto;
import dev.shirleano.classmaster_backend.exceptions.AlunoNotFoundException;
import dev.shirleano.classmaster_backend.exceptions.InvalidOrExpiredTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InvalidOrExpiredTokenException.class)
    public ResponseEntity<ExceptionDto> InvalidOrExpiredTokenException(InvalidOrExpiredTokenException exception) {
        return ResponseEntity.badRequest().body(new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ExceptionDto> TokenCreationException(JWTCreationException exception) {
        return ResponseEntity.badRequest().body(new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<ExceptionDto> AlunoNotFoundException(AlunoNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

}
