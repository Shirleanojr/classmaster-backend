package dev.shirleano.classmaster_backend.dto.aluno;

import dev.shirleano.classmaster_backend.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoAlunoDTO(
        @NotNull
        Long id,
        String nome,
        String sobrenome,
        String email,
        String telefone,
        String telefoneOutro,
        Endereco endereco,
        String instagram,
        String facebook

) {
}
