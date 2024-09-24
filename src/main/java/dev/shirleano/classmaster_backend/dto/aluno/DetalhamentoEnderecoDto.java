package dev.shirleano.classmaster_backend.dto.aluno;

public record DetalhamentoEnderecoDto(

        Long id,
        String cep,
        String rua,
        String numero,
        String complemento,
        String cidade,
        String bairro,
        String estado,
        String pais

) {
}
