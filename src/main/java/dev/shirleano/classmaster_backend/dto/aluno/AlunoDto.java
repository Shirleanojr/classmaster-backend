package dev.shirleano.classmaster_backend.dto.aluno;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDto(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        String dataNascimento,
        @NotBlank
        String cpf,
        @NotBlank
        String email,
        @NotNull
        String telefone,
        String telefoneOutro,
        @NotNull
        EnderecoDto endereco,

        String instagram,
        String facebook
) {

}
