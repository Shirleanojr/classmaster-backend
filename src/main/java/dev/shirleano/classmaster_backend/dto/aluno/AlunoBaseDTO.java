package dev.shirleano.classmaster_backend.dto.aluno;

import dev.shirleano.classmaster_backend.domain.aluno.Aluno;

import java.time.format.DateTimeFormatter;

public record AlunoBaseDTO(
        Long matricula,
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        String dataAdmissao

) {

    public AlunoBaseDTO(Aluno aluno) {
        this(aluno.getId(),
                aluno.getNome(),
                aluno.getSobrenome() ,
                aluno.getCpf(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
    }
}
