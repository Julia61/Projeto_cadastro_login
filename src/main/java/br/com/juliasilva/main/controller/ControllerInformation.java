package br.com.juliasilva.main.controller;

import br.com.juliasilva.main.exception.UserException;
import br.com.juliasilva.main.repository.UserRepository;
import br.com.juliasilva.main.user.EntityRegister;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ControllerInformation {



    @Autowired
    private UserRepository userRepository;

    @Tag(name = "Verificar cadastro",description = "Informações de cadastro")
    @Operation(summary = "Verificar informações de cadastro",
            description = "Essa função é responsável por mostrar os dados de cadastro do usuário." +
                    " O token é enviado automaticamente via cookie, portanto, não é necessário informá-lo diretamente na requisição.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = EntityRegister.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = String.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")

    @GetMapping("/dados-token")
    public ResponseEntity<Object> getTokenData(HttpServletRequest request) {
        try {

            Optional<Cookie> authTokenCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> "authToken".equals(cookie.getName()))
                    .findFirst();

            if (authTokenCookie.isEmpty()) {
                return ResponseEntity.status(401).body("Token não encontrado. Faça login novamente.");
            }

            String token = authTokenCookie.get().getValue();

            DecodedJWT decodedJWT = JWT.decode(token);

            String email = decodedJWT.getClaim("email").asString();

            var encontrar = userRepository.findByEmail(email);

            if (encontrar.isEmpty()) {
                return ResponseEntity.status(404).body("Usuário não encontrado.");
            }

            var usuario = encontrar.get().getUsuario();

            var senha = encontrar.get().getSenha();


            return ResponseEntity.ok("Usuário: " + usuario + "\n" + "Email: " + email + "\n" + "Senha: " + senha);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar o token: " + e.getMessage());
        }
    }

}
