package br.com.juliasilva.main.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Repository
public class AuthDTO {

    @Schema(example = "maria@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(example = "admin@1234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;

}
