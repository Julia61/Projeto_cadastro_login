package br.com.juliasilva.main.useCase;


import br.com.juliasilva.main.dto.AuthDTO;

import br.com.juliasilva.main.dto.AuthUserRequestDTO;
import br.com.juliasilva.main.dto.AuthUserResponseDTO;
import br.com.juliasilva.main.repository.RegisterRepository;
import br.com.juliasilva.main.repository.LoginRepository;

import br.com.juliasilva.main.user.EntityRegister;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;


@Service
public class LoginRegistrationService {

    @Value("${security.token.secret}")
    private String chaveSecreta;


    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityRegister entityRegister;

    public AuthUserResponseDTO execute(AuthUserRequestDTO authUserRequestDTO) throws AuthenticationException {
        var user = this.loginRepository.findByEmail(authUserRequestDTO.email())
                .orElseThrow(()-> {
                    throw new UsernameNotFoundException("Email/password incorrect");
                });

        var passwordMatches = this.passwordEncoder.matches(authUserRequestDTO.senha(), user.getSenha());

        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        var roles = Arrays.asList("USER");

        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(30));

        var token = JWT.create()
                .withIssuer("login-app")
                .withSubject(user.getId().toString())
                .withClaim("roles", roles)
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authUserResponse = AuthUserResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .roles(roles)
                .build();
        return authUserResponse;
    }

}
