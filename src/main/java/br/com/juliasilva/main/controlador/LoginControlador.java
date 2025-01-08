package br.com.juliasilva.main.controlador;

import br.com.juliasilva.main.casoDeUso.LoginUsuario;
import br.com.juliasilva.main.dto.AutorDTO;
import br.com.juliasilva.main.provedor.JWTProvedor;
import br.com.juliasilva.main.repositorio.LoginRepositorio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cadastro")
public class LoginControlador {

    @Autowired
    private LoginUsuario loginUsuario;

    @Autowired
    private JWTProvedor jwtProvedor;

    @Autowired
    private LoginRepositorio loginRepositorio;
    //preciso comparar as senha as credencia

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AutorDTO autorDTO, HttpServletResponse response){

        try {
            boolean resultado = this.loginUsuario.execute(autorDTO);

            if (resultado) {

                String token = jwtProvedor.gerarToken(autorDTO);

                Cookie cookie = new Cookie("authToken", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(30));

                response.addCookie(cookie);

                return ResponseEntity.ok(token);
            }else {
                return ResponseEntity.status(401).body("Email ou senha inválidos.");
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }

}
