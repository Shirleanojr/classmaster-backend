package dev.shirleano.classmaster_backend.repository;

import dev.shirleano.classmaster_backend.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
