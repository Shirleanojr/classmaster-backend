package dev.shirleano.classmaster_backend.services.aluno;

import dev.shirleano.classmaster_backend.domain.aluno.Aluno;
import dev.shirleano.classmaster_backend.domain.endereco.Endereco;
import dev.shirleano.classmaster_backend.dto.aluno.AlunoDto;
import dev.shirleano.classmaster_backend.dto.aluno.EnderecoDto;
import dev.shirleano.classmaster_backend.repository.AlunoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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

    @Mock
    private Aluno aluno;

    @Mock
    private Endereco endereco;

    @Captor
    private ArgumentCaptor<Aluno> alunoCaptor;

    @Test
    @DisplayName("Deve realizar o cadastro do aluno com sucesso")
    void cadastrarAluno() {
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

        given(enderecoService.criarEndereco(enderecoDto)).willReturn(endereco);
        given(aluno.getId()).willReturn(10L);
        given(aluno.getCpf()).willReturn(alunoDto.cpf());

        alunoService.cadastrarAluno(alunoDto);

        then(alunoRepository).should().save(alunoCaptor.capture());
        Aluno alunoSalvo = alunoCaptor.getValue();

        assertEquals(aluno.getCpf(), alunoSalvo.getCpf());
    }

    @Test
    void detalharDadosAluno() {
    }

    @Test
    void listarTodosAlunos() {
    }

    @Test
    void listarAlunosPeloNome() {
    }

    @Test
    void listarAlunosPorMatricula() {
    }

    @Test
    void atualizarDadosAluno() {
    }
}