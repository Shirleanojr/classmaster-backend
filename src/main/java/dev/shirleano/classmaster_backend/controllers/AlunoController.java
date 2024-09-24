package dev.shirleano.classmaster_backend.controllers;

import dev.shirleano.classmaster_backend.dto.aluno.AlunoDto;
import dev.shirleano.classmaster_backend.dto.aluno.AlunoPageDTO;
import dev.shirleano.classmaster_backend.dto.aluno.AtualizacaoAlunoDTO;
import dev.shirleano.classmaster_backend.dto.aluno.DetalhamentoAlunoDto;
import dev.shirleano.classmaster_backend.services.aluno.AlunoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;


    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoAlunoDto> cadastrarAluno(@RequestBody AlunoDto dados, UriComponentsBuilder uriBuilder) {
        DetalhamentoAlunoDto novoAluno = service.cadastrarAluno(dados);
        URI uri =  uriBuilder.path("aluno/{id}").buildAndExpand(novoAluno.id()).toUri();
        return ResponseEntity.created(uri).body(novoAluno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoAlunoDto> detalharAluno(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalharDadosAluno(id));
    }

    @GetMapping
    public AlunoPageDTO listarTodosAlunos(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                          @RequestParam(defaultValue = "10") @Positive @Max(10) int size) {
        return service.listarTodosAlunos(page, size);
    }

    @GetMapping("/nome/{nome}")
    public AlunoPageDTO listarAlunosPorNome(@PathVariable() String nome,
                                            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                            @RequestParam(defaultValue = "10") @Positive @Max(10) int size) {

        return service.listarAlunosPeloNome(nome, page, size);

    }

    @GetMapping("/matricula/{matricula}")
    public AlunoPageDTO listarAlunosPorMatricula(@PathVariable() Long matricula,
                                            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                            @RequestParam(defaultValue = "10") @Positive @Max(10) int size) {

        return service.listarAlunosPorMatricula(matricula, page, size);

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoAlunoDto> atualizarDadosAluno(@RequestBody AtualizacaoAlunoDTO dados) {
        DetalhamentoAlunoDto alunoAtualizado = service.atualizarDadosAluno(dados);
        return ResponseEntity.ok(alunoAtualizado);
    }
}
