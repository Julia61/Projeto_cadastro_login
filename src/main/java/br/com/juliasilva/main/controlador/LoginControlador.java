package br.com.juliasilva.main.controlador;


import br.com.juliasilva.main.casoDeUso.LoginUsuario;
import br.com.juliasilva.main.usuario.CadrastroEntidade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class LoginControlador {

    @Autowired
    private LoginUsuario loginUsuario;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody CadrastroEntidade cadrastroEntidade){
        try {
            boolean resultado = this.loginUsuario.execute(cadrastroEntidade);
            if(resultado) {
                return ResponseEntity.ok().body("Login realizado com sucesso.");
            }else {
                return ResponseEntity.status(401).body("Email ou senha inválidos.");
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }

}
