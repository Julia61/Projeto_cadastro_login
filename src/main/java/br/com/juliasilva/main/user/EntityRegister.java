package br.com.juliasilva.main.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "cadastro_login")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class EntityRegister {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true)
    private UUID id;


    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(example = "Maria", requiredMode = Schema.RequiredMode.REQUIRED)
    private String usuario;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Schema(example = "maria@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(min = 10, max = 70, message = "A senha deve conter entre (10) e (70) caracteres")
    @Schema(example = "admin123456789", minLength = 10, maxLength = 70 ,requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;

    @CreationTimestamp
    @Schema(hidden = true)
    private LocalDateTime criadoEm;
}
