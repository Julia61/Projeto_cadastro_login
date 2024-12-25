package br.com.juliasilva.main.controlador;

import br.com.juliasilva.main.casoDeUso.CriarCadrastroUsuario;
import br.com.juliasilva.main.usuario.CadrastroEntidade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/cadastro")
public class CadastroControlador {

    @Autowired
    private CriarCadrastroUsuario criarCadrastroUsuario;

    @PostMapping("/usuario")
    public ResponseEntity<Object> usuario(@Valid @RequestBody CadrastroEntidade cadrastroEntidade){
        try {
            var resultado = this.criarCadrastroUsuario.execute(cadrastroEntidade);
            return ResponseEntity.ok().body(resultado);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
