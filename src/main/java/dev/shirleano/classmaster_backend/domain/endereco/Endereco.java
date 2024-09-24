package dev.shirleano.classmaster_backend.domain.endereco;

import dev.shirleano.classmaster_backend.dto.aluno.EnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco(EnderecoDto endereco) {
        this.setCep(endereco.cep());
        this.setRua(endereco.rua());
        this.setNumero(endereco.numero());
        this.setComplemento(endereco.complemento());
        this.setBairro(endereco.bairro());
        this.setCidade(endereco.cidade());
        this.setEstado(endereco.estado());
        this.setPais(endereco.pais());
    }


    public void atualizarDados(Endereco dados) {
        if (dados.cep != null) {this.setCep(dados.cep);}
        if (dados.rua != null) {this.setRua(dados.rua);}
        if (dados.numero != null) {this.setNumero(dados.numero);}
        if (dados.complemento != null) {this.setComplemento(dados.complemento);}
        if (dados.bairro != null) {this.setBairro(dados.bairro);}
        if (dados.cidade != null) {this.setCidade(dados.cidade);}
        if (dados.estado != null) {this.setEstado(dados.estado);}
        if (dados.pais != null) {this.setPais(dados.pais);}
    }
}
