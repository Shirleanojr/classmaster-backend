package dev.shirleano.classmaster_backend.exceptions;

public class AlunoNotFoundException extends RuntimeException {

    public AlunoNotFoundException(String message) {
        super("Aluno não encontrado - " + message);
    }
}
