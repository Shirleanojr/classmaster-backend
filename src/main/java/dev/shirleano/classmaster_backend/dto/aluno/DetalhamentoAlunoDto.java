package dev.shirleano.classmaster_backend.dto.aluno;

import dev.shirleano.classmaster_backend.domain.aluno.Aluno;
import dev.shirleano.classmaster_backend.domain.endereco.Endereco;

import java.time.format.DateTimeFormatter;

public record DetalhamentoAlunoDto(

        Long id,
        String nome,
        String sobrenome,
        String email,
        String cpf,
        String dataNascimento,
        String telefone,
        String telefoneOutro,
        Endereco endereco,
        String instagram,
        String facebook,
        String matricula,
        String dataMatricula
) {
    public DetalhamentoAlunoDto(Aluno aluno) {
        this(aluno.getId(),
                aluno.getNome(),
                aluno.getSobrenome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getDataNascimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                (aluno.getTelefone() == null) ? "" : aluno.getTelefone(),
                (aluno.getTelefoneOutro() == null) ? "" : aluno.getTelefoneOutro(),
                aluno.getEndereco(),
                (aluno.getInstagram() == null) ? "" : aluno.getInstagram(),
                (aluno.getFacebook() == null) ? "" : aluno.getFacebook(),
                aluno.getId().toString(),
                aluno.getDataCriacao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
