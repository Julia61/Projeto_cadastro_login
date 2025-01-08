package br.com.juliasilva.main.provedor;

import br.com.juliasilva.main.dto.AutorDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JWTProvedor {

    @Value("${security.token.secret}")
    private String chaveSecreta;


    public String gerarToken(AutorDTO autorDTO){
        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
        return JWT.create()
                .withIssuer("login-app")
                .withClaim("email", autorDTO.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .sign(algorithm);

    }

    public boolean tokenValido(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            JWT.require(algorithm).withIssuer("login-app").build().verify(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }


}
