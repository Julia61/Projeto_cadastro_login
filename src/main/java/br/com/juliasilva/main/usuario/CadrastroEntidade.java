package br.com.juliasilva.main.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "cadastro_login")
public class CadrastroEntidade {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank()

    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    private String usuario;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Length(min = 10, max = 70, message = "A senha deve conter entre (10) e (70) caracteres")
    private String senha;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
