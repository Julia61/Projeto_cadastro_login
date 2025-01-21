package br.com.juliasilva.main.repository;

import br.com.juliasilva.main.user.EntityRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<EntityRegister, UUID> {
    Optional<EntityRegister> findByEmail(String email);
}
