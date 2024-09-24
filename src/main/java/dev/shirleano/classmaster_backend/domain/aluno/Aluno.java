package dev.shirleano.classmaster_backend.domain.aluno;

import dev.shirleano.classmaster_backend.domain.endereco.Endereco;
import dev.shirleano.classmaster_backend.dto.aluno.AlunoDto;
import dev.shirleano.classmaster_backend.dto.aluno.AtualizacaoAlunoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String telefone;
    private String telefoneOutro;
    @OneToOne()
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String instagram;
    private String facebook;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataAtualizacao;

    public Aluno(AlunoDto dados) {
        this.setNome(dados.nome());
        this.setSobrenome(dados.sobrenome());
        this.setCpf(dados.cpf());
        this.setEmail(dados.email());
        this.setTelefone(dados.telefone());
        this.setTelefoneOutro(dados.telefoneOutro());
        this.setDataNascimento(LocalDate.parse(dados.dataNascimento()));
        this.setInstagram(dados.instagram());
        this.setFacebook(dados.facebook());
        this.setDataAtualizacao(this.dataCriacao);
        this.setEndereco(new Endereco(dados.endereco()));
    }


    public void atualizarDadosAluno(AtualizacaoAlunoDTO dados) {
        if (dados.nome() != null) {this.setNome(dados.nome());}
        if (dados.sobrenome() != null) {this.setSobrenome(dados.sobrenome());}
        if (dados.email() != null) {this.setEmail(dados.email());}
        if (dados.telefone() != null) {this.setTelefone(dados.telefone());}
        if (dados.telefoneOutro() != null) {this.setTelefoneOutro(dados.telefoneOutro());}
        if (dados.endereco() != null) {this.endereco.atualizarDados(dados.endereco());}
        if (dados.instagram() != null) {this.setInstagram(dados.instagram());}
        if (dados.facebook() != null) {this.setFacebook(dados.facebook());}
    }
}
