package br.com.juliasilva.main.repositorio;

import br.com.juliasilva.main.usuario.CadrastroEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CadrastroRepositorio extends JpaRepository<CadrastroEntidade, UUID> {
    Optional<CadrastroEntidade> findByUsuarioOrEmail(String usuario, String email);
}
