package br.com.juliasilva.main.provider;

import br.com.juliasilva.main.dto.AuthDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String chaveSecreta;


    public DecodedJWT validateToken(String token){
        token = token.replace("Bearer", "").trim();

        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);

        try {
            var tokenDecoded = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return tokenDecoded;
        }catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }



}
