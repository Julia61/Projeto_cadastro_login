package br.com.juliasilva.main.repository;

import br.com.juliasilva.main.user.EntityRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegisterRepository extends JpaRepository<EntityRegister, UUID> {
    Optional<EntityRegister> findByUsuarioOrEmail(String usuario, String email);
}
