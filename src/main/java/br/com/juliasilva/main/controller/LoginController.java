package br.com.juliasilva.main.controller;

import br.com.juliasilva.main.useCase.LoginRegistrationService;
import br.com.juliasilva.main.dto.AuthDTO;
import br.com.juliasilva.main.exception.EmailNotFoundExceptionOrPasswordWrong;
import br.com.juliasilva.main.provider.JWTProvider;
import br.com.juliasilva.main.repository.LoginRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cadastro")
public class LoginController {

    @Autowired
    private LoginRegistrationService loginRegistrationService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private LoginRepository loginRepositorio;
    //preciso comparar as senha as credencia

    @PostMapping("/login")
    @Tag(name = "Login", description = "Dados de login do usuário")
    @Operation(summary = "Login do usuário", description = "Essa função é responsável por fazer o login do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthDTO.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = String.class))
            })
    })
    public ResponseEntity<Object> login(@Valid @RequestBody AuthDTO autorDTO, HttpServletResponse response){


            boolean resultado = this.loginRegistrationService.execute(autorDTO);

            if (!resultado) {
                throw new EmailNotFoundExceptionOrPasswordWrong();
            }

                String token = jwtProvider.gerarToken(autorDTO);

                Cookie cookie = new Cookie("authToken", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge((int) TimeUnit.MINUTES.toMinutes(30));

                response.addCookie(cookie);

                return ResponseEntity.ok(token);

    }


}
