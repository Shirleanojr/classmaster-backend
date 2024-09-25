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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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

    @Mock
    private Aluno aluno;

    @Mock
    private Endereco endereco;

    @Captor
    private ArgumentCaptor<Aluno> alunoCaptor;

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
    }

    @Test
    @DisplayName("Deve realizar o cadastro do aluno com sucesso")
    void cadastrarAluno() {

        given(enderecoService.criarEndereco(enderecoDto)).willReturn(endereco);
        given(aluno.getId()).willReturn(10L);
        given(aluno.getCpf()).willReturn(alunoDto.cpf());

        alunoService.cadastrarAluno(alunoDto);

        then(alunoRepository).should().save(alunoCaptor.capture());
        Aluno alunoSalvo = alunoCaptor.getValue();

        assertEquals(aluno.getCpf(), alunoSalvo.getCpf());
    }

    @Test
    @DisplayName("Deveria não lançar a exceção e encontrar o aluno ")
    void detalharDadosAlunoSucesso() {
        given(alunoRepository.getReferenceById(1L)).willReturn(aluno);
        given(aluno.getDataNascimento()).willReturn(LocalDate.now());
        given(aluno.getDataCriacao()).willReturn(LocalDateTime.now());
        assertDoesNotThrow(() -> alunoService.detalharDadosAluno(1L));
    }

    @Test
    @DisplayName("Deveria  lançar a exceção ao não encontrar o aluno ")
    void detalharDadosAlunoFalha() {
        given(alunoRepository.getReferenceById(1L)).willReturn(null);
        assertThrows(AlunoNotFoundException.class, () -> alunoService.detalharDadosAluno(1L));
    }

    @Test
    @DisplayName("Deveria realizar a atualização do email do aluno")
    void atualizarDadosAlunoSucesso() {
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


        given(alunoRepository.getReferenceById(1L)).willReturn(aluno);
        given(aluno.getDataNascimento()).willReturn(LocalDate.now());
        given(aluno.getDataCriacao()).willReturn(LocalDateTime.now());

        alunoService.atualizarDadosAluno(atualizacaoAlunoDTO);

        verify(aluno, times(1)).atualizarDadosAluno(atualizacaoAlunoDTO);
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    @DisplayName("Deveria lançar exceção ao tentar atualizar aluno não existente")
    void atualizarDadosAlunoFalhaCenario1() {
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

        given(alunoRepository.getReferenceById(1L)).willReturn(null);
        given(aluno.getDataNascimento()).willReturn(LocalDate.now());
        given(aluno.getDataCriacao()).willReturn(LocalDateTime.now());

        assertThrows(AlunoNotFoundException.class, () -> alunoService.atualizarDadosAluno(atualizacaoAlunoDTO));
    }
}