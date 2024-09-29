package dev.shirleano.classmaster_backend.services.aluno;

import dev.shirleano.classmaster_backend.domain.aluno.Aluno;
import dev.shirleano.classmaster_backend.domain.endereco.Endereco;
import dev.shirleano.classmaster_backend.dto.aluno.AlunoDto;
import dev.shirleano.classmaster_backend.dto.aluno.AtualizacaoAlunoDTO;
import dev.shirleano.classmaster_backend.dto.aluno.EnderecoDto;
import dev.shirleano.classmaster_backend.exceptions.AlunoNotFoundException;
import dev.shirleano.classmaster_backend.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private EnderecoService enderecoService;

    private AlunoDto alunoDto;
    private EnderecoDto enderecoDto;
    private AtualizacaoAlunoDTO atualizacaoAlunoDTO;
    private Aluno aluno;

    @Mock
    private Endereco endereco;

    @Captor
    private ArgumentCaptor<Aluno> alunoCaptor;

    @Captor
    private ArgumentCaptor<Long> idAlunoCaptor;

    @BeforeEach
    void setUp() {
        this.enderecoDto = new EnderecoDto(
                "88000-000",
                "Rua sem nome",
                "0",
                "casa engraçada",
                "ali",
                "itapoa",
                "sc",
                "BR"
        );

        this.alunoDto = new AlunoDto(
                "Fulano",
                "De tal",
                "1999-01-01",
                "000000000",
                "fulano.de.tal@email.com",
                "48999999999",
                "",
                enderecoDto,
                "",
                "");

        this.aluno = new Aluno(1L, alunoDto);

        this.atualizacaoAlunoDTO = new AtualizacaoAlunoDTO(
                1L,
                null,
                null,
                "fulano.total@email.com",
                null,
                null,
                null,
                null,
                null
        );
    }

    @Nested
    class cadastrarAluno {
        @Test
        @DisplayName("Deveia realizar o cadastro do aluno com sucesso")
        void DeveriaCadastrarAlunoComSucesso() {

            doReturn(aluno).when(alunoRepository).save(alunoCaptor.capture());
            var output = alunoService.cadastrarAluno(alunoDto);

            var alunoCaptured = alunoCaptor.getValue();

            assertNotNull(output);
            assertEquals(alunoDto.nome(), alunoCaptured.getNome());
            assertEquals(alunoDto.sobrenome(), alunoCaptured.getSobrenome());
            assertEquals(alunoDto.cpf(), alunoCaptured.getCpf());
            assertEquals(alunoDto.dataNascimento(), alunoCaptured.getDataNascimento().toString());
            assertEquals(alunoDto.email(), alunoCaptured.getEmail());

            verify(alunoRepository, times(1)).save(alunoCaptured);
        }

        @Test
        @DisplayName("Deveria retorar excecao quando um erro acontecer")
        void DeveriaRetorarExcecaoQuandoAlgumErroAcontecer() {
            doThrow(new RuntimeException()).when(alunoRepository).save(any());
            assertThrows(RuntimeException.class, () -> alunoService.cadastrarAluno(alunoDto));
        }
    }

    @Nested
    class detalharDadosAluno {
        @Test
        @DisplayName("Deveria retornar os dados do aluno com sucesso ")
        void detalharDadosAlunoSucesso() {

            doReturn(aluno).when(alunoRepository).getReferenceById(idAlunoCaptor.capture());
            var output = alunoService.detalharDadosAluno(1L);
            assertNotNull(output);
            assertEquals(aluno.getId(), idAlunoCaptor.getValue());

        }

        @Test
        @DisplayName("Deveria  lançar a exceção ao não encontrar o aluno ")
        void detalharDadosAlunoFalha() {
            doReturn(null).when(alunoRepository).getReferenceById(1L);
            assertThrows(AlunoNotFoundException.class, () -> alunoService.detalharDadosAluno(1L));
        }
    }

    @Nested
    class listarAlunos {

        @Test
        @DisplayName("Deveria retornar todos os alunos caso sucesso")
        void deveriaRetornarTodosAlunosCasoSucesso() {
            Page<Aluno> pageAluno = new PageImpl<>(
                    List.of(aluno),
                    PageRequest.of(0,10, Sort.by("nome").ascending()),
                    1);
            doReturn(pageAluno).when(alunoRepository).findAll(any(Pageable.class));
            var output = alunoService.listarTodosAlunos(0, 10);
            assertNotNull(output);
            assertEquals(1, output.alunos().size());
            verify(alunoRepository, times(1)).findAll(any(Pageable.class));
        }

        @Test
        @DisplayName("Deveria lancar excessao quando o alunos não encontrados")
        void deveriaRetornarExcessaoQuandoAlunosNaoEncontrados() {
            doReturn(null).when(alunoRepository).findAll(any(Pageable.class));
            assertThrows(AlunoNotFoundException.class, () -> alunoService.listarTodosAlunos(0, 10));
            verify(alunoRepository, times(1)).findAll(any(Pageable.class));
        }

        @Test
        @DisplayName("Deveria retornar todos os alunos filtrados pelo nome caso sucesso")
        void deveriaRetornarTodosAlunosFiltradosPeloNomeCasoSucesso() {
            Pageable pageable = PageRequest.of(0,10, Sort.by("nome").ascending());
            Page<Aluno> pageAluno = new PageImpl<>(
                    List.of(aluno),
                    pageable,
                    1);
            doReturn(pageAluno).when(alunoRepository).findByNomeIgnoreCaseContaining(aluno.getNome(), pageable);
            var output = alunoService.listarAlunosPeloNome(aluno.getNome(), 0, 10);
            assertNotNull(output);
            assertEquals(1, output.alunos().size());
            assertEquals(aluno.getNome(), output.alunos().get(0).nome());
            verify(alunoRepository, times(1)).findByNomeIgnoreCaseContaining(aluno.getNome(), pageable);
        }

        @Test
        @DisplayName("Deveria retornar todos os alunos filtrados pela marticula caso sucesso")
        void deveriaRetornarTodosAlunosFiltradosPelaMatriculaCasoSucesso() {
            Pageable pageable = PageRequest.of(0,10, Sort.by("nome").ascending());
            Page<Aluno> pageAluno = new PageImpl<>(
                    List.of(aluno),
                    pageable,
                    1);
            doReturn(pageAluno).when(alunoRepository).findById(aluno.getId(), pageable);
            var output = alunoService.listarAlunosPorMatricula(aluno.getId(), 0, 10);
            assertNotNull(output);
            assertEquals(1, output.alunos().size());
            assertEquals(aluno.getNome(), output.alunos().get(0).nome());
            verify(alunoRepository, times(1)).findById(aluno.getId(), pageable);
        }
    }

    @Nested
    class atualizarDadosAluno {
        @Test
        @DisplayName("Deveria realizar a atualização do email do aluno")
        void atualizarDadosAlunoSucesso() {

            doReturn(aluno).when(alunoRepository).getReferenceById(1L);
            doReturn(aluno).when(alunoRepository).save(alunoCaptor.capture());

            var output = alunoService.atualizarDadosAluno(atualizacaoAlunoDTO);
            var alunoCaptured = alunoCaptor.getValue();

            assertEquals(output.email(), alunoCaptured.getEmail());
            verify(alunoRepository, times(1)).save(alunoCaptured);
        }

        @Test
        @DisplayName("Deveria lançar exceção ao tentar atualizar aluno não existente")
        void atualizarDadosAlunoFalhaCenario1() {
            doReturn(null).when(alunoRepository).getReferenceById(1L);
            assertThrows(AlunoNotFoundException.class, () -> alunoService.atualizarDadosAluno(atualizacaoAlunoDTO));
            verify(alunoRepository, times(0)).save(any());
        }
    }






}