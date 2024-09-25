package dev.shirleano.classmaster_backend.services.aluno;

import dev.shirleano.classmaster_backend.domain.aluno.Aluno;
import dev.shirleano.classmaster_backend.dto.aluno.*;
import dev.shirleano.classmaster_backend.exceptions.AlunoNotFoundException;
import dev.shirleano.classmaster_backend.repository.AlunoRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private EnderecoService enderecoService;


    public DetalhamentoAlunoDto cadastrarAluno(AlunoDto dados) {
        Aluno novoAluno = new Aluno(dados);
        novoAluno.setEndereco(enderecoService.criarEndereco(dados.endereco()));
        repository.save(novoAluno);
        return new DetalhamentoAlunoDto(novoAluno);
    }

    public DetalhamentoAlunoDto detalharDadosAluno(Long id) {
        try {
            Aluno aluno = repository.getReferenceById(id);
            return new DetalhamentoAlunoDto(aluno);
        }catch (Exception ex){
            throw new AlunoNotFoundException(ex.getMessage());
        }
    }

    public AlunoPageDTO listarTodosAlunos(@PositiveOrZero int page, @Positive @Max(10) int size){
        Page<Aluno> pageAluno = repository.findAll(PageRequest.of(page, size, Sort.by("nome").ascending()));
        List<AlunoBaseDTO> alunos = pageAluno.get().map(AlunoBaseDTO::new).collect(Collectors.toList());
        return new AlunoPageDTO(alunos, pageAluno);
    }

    public AlunoPageDTO listarAlunosPeloNome(String nome, @PositiveOrZero int page, @Positive @Max(10) int size) {
        try {
            Page<Aluno> pageAluno = repository.findByNomeIgnoreCaseContaining(nome, PageRequest.of(page, size, Sort.by("nome").ascending()));
            List<AlunoBaseDTO> alunos = pageAluno.get().map(AlunoBaseDTO::new).collect(Collectors.toList());
            return new AlunoPageDTO(alunos, pageAluno);
        } catch (Exception ex) {
            throw new AlunoNotFoundException(ex.getMessage());
        }

    }

    public AlunoPageDTO listarAlunosPorMatricula(Long mastricula, @PositiveOrZero int page, @Positive @Max(10) int size) {
        try {
            Page<Aluno> pageAluno = repository.findById(mastricula, PageRequest.of(page, size, Sort.by("nome").ascending()));
            List<AlunoBaseDTO> alunos = pageAluno.get().map(AlunoBaseDTO::new).collect(Collectors.toList());
            return new AlunoPageDTO(alunos, pageAluno);
        } catch(Exception ex) {
            throw new AlunoNotFoundException(ex.getMessage());
        }
    }


    public DetalhamentoAlunoDto atualizarDadosAluno(AtualizacaoAlunoDTO dados) {
        try {
            Aluno aluno = repository.getReferenceById(dados.id());
            aluno.atualizarDadosAluno(dados);
            repository.save(aluno);
            return new DetalhamentoAlunoDto(aluno);
        } catch (Exception ex) {
            throw new AlunoNotFoundException(ex.getMessage());
        }
    }
}
