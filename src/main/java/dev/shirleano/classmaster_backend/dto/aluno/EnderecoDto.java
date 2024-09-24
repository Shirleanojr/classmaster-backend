package dev.shirleano.classmaster_backend.dto.aluno;

public record EnderecoDto(
        String cep,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String pais
) {
}
