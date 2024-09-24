package dev.shirleano.classmaster_backend.dto.aluno;

import dev.shirleano.classmaster_backend.domain.aluno.Aluno;
import org.springframework.data.domain.Page;

import java.util.List;

public record AlunoPageDTO(
        List<AlunoBaseDTO> alunos,
        int first,
        int prev,
        int next,
        int last,
        int pages,
        long totalElements,
        int totalPages
) {
    public AlunoPageDTO(List<AlunoBaseDTO> alunos, Page<Aluno> pageAluno) {
        this(alunos,
                (pageAluno.getNumber() > 0 ? pageAluno.getNumber() - 1 : 0),
                (pageAluno.hasPrevious() ? pageAluno.getNumber() - 1: 0),
                (pageAluno.hasNext() ? pageAluno.getNumber() + 1 : pageAluno.getNumber()),
                (pageAluno.getTotalPages() - 1),
                (pageAluno.getTotalPages()),
                pageAluno.getTotalElements(),
                pageAluno.getTotalPages());

    }
}
