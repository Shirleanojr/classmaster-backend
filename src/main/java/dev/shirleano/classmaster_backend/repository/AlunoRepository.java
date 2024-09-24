package dev.shirleano.classmaster_backend.repository;

import dev.shirleano.classmaster_backend.domain.aluno.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Page<Aluno> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);
    Page<Aluno> findById(Long mastricula, Pageable pageable);
}
