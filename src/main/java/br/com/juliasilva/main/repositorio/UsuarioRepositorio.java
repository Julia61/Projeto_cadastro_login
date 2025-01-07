package br.com.juliasilva.main.repositorio;

import br.com.juliasilva.main.usuario.CadrastroEntidade;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepositorio extends JpaRepository<CadrastroEntidade, UUID> {
    Optional<CadrastroEntidade> findByEmail(String email);
}
