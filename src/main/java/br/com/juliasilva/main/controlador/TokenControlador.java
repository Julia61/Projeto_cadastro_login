package br.com.juliasilva.main.controlador;

import br.com.juliasilva.main.casoDeUso.TokenServico;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class TokenControlador {

    @Autowired
    private TokenServico tokenServico;

    @GetMapping("/validate-token")
    public ResponseEntity<String> validarToken(HttpServletRequest request){
        return tokenServico.validarToken(request);
    }


}
