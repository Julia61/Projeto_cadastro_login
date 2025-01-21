package br.com.juliasilva.main;

import br.com.juliasilva.main.dto.AuthDTO;
import br.com.juliasilva.main.exception.EmailNotFoundExceptionOrPasswordWrong;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Duration;
import java.time.Instant;


public class CreatToken {

    public static String generateToken(AuthDTO autorDTO, String secret){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            var expiresIn = Instant.now().plus(Duration.ofMinutes(30));

            var token = JWT.create().withIssuer("login-app")
                    .withClaim("email", autorDTO.getEmail())
                    .withExpiresAt(expiresIn)
                    .sign(algorithm);

            return token;
        }catch (Exception e){
            e.printStackTrace();
            throw new EmailNotFoundExceptionOrPasswordWrong();


        }


    }

}
