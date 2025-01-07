package br.com.juliasilva.main.casoDeUso;

import br.com.juliasilva.main.provedor.JWTProvedor;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenServico {

    @Autowired
    private JWTProvedor jwtProvedor;

    public ResponseEntity<String> validarToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;

        if(cookies != null){
            for(Cookie cookie : cookies){
                if("authToken".equals(cookie.getName())){
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if(token != null && jwtProvedor.tokenValido(token)){
            return ResponseEntity.ok("Token válido");
        }else {
            return ResponseEntity.status(401).body("Token expirado ou inválido");
        }

    }

}
