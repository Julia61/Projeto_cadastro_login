package br.com.juliasilva.main.repositorio;

import br.com.juliasilva.main.usuario.CadrastroEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepositorio extends JpaRepository<CadrastroEntidade, Long> {
    Optional<CadrastroEntidade> findByUsuarioAndEmailAndSenha(String usuario, String email, String senha);
}
