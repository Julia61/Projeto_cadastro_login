package br.com.juliasilva.main.useCase;

import br.com.juliasilva.main.provider.JWTProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private JWTProvider jwtProvider;

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

        if(token != null && jwtProvider.tokenValido(token)){
            return ResponseEntity.ok("Token válido");
        }else {
            return ResponseEntity.status(401).body("Token expirado ou inválido");
        }

    }

}
