package dev.shirleano.classmaster_backend.infra.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import dev.shirleano.classmaster_backend.dto.exception.ExceptionDto;
import dev.shirleano.classmaster_backend.exceptions.InvalidOrExpiredTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InvalidOrExpiredTokenException.class)
    public ResponseEntity<ExceptionDto> InvalidOrExpiredTokenException(InvalidOrExpiredTokenException exception) {
        return ResponseEntity.badRequest().body(new ExceptionDto(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ExceptionDto> TokenCreationException(JWTCreationException exception) {
        return ResponseEntity.badRequest().body(new ExceptionDto(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

}
