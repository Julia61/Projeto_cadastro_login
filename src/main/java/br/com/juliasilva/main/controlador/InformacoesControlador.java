package br.com.juliasilva.main.controlador;

import br.com.juliasilva.main.provedor.JWTProvedor;
import br.com.juliasilva.main.repositorio.UsuarioRepositorio;
import br.com.juliasilva.main.usuario.CadrastroEntidade;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
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
public class InformacoesControlador {

    @Autowired
    private JWTProvedor jwtProvedor;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
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

            var encontrar = usuarioRepositorio.findByEmail(email);
            var usuario = encontrar.get().getUsuario();
            var senha = encontrar.get().getSenha();


            return ResponseEntity.ok("Usuário: " + usuario + "\n" + "Email: " + email + "\n" + "Senha: " + senha);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar o token: " + e.getMessage());
        }
    }

}
