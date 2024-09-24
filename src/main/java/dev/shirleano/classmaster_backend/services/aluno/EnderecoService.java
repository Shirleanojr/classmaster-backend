package dev.shirleano.classmaster_backend.services.aluno;

import dev.shirleano.classmaster_backend.domain.endereco.Endereco;
import dev.shirleano.classmaster_backend.dto.aluno.DetalhamentoEnderecoDto;
import dev.shirleano.classmaster_backend.dto.aluno.EnderecoDto;
import dev.shirleano.classmaster_backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco criarEndereco(EnderecoDto endereco) {
        Endereco novoEndereco =  new Endereco(endereco);
        repository.save(novoEndereco);
        return novoEndereco;
    }

    public DetalhamentoEnderecoDto montarDetalhamentoEnderecoRetorno(Endereco dados) {
        DetalhamentoEnderecoDto retorno = new DetalhamentoEnderecoDto(
                dados.getId(),
                dados.getCep(),
                dados.getCidade(),
                dados.getNumero(),
                dados.getComplemento(),
                dados.getCidade(),
                dados.getBairro(),
                dados.getEstado(),
                dados.getPais()
        );

        return retorno;
    }
}
