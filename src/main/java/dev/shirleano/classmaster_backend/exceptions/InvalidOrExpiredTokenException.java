package dev.shirleano.classmaster_backend.exceptions;

public class InvalidOrExpiredTokenException extends RuntimeException{

    public InvalidOrExpiredTokenException() {
        super("Token inválido ou expirado");
    }

    public InvalidOrExpiredTokenException(String message) {
        super(message);
    }
}
