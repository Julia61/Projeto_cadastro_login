package br.com.juliasilva.main.controller;


import br.com.juliasilva.main.dto.AuthUserRequestDTO;
import br.com.juliasilva.main.exception.EmailNotFoundExceptionOrPasswordWrong;
import br.com.juliasilva.main.provider.JWTProvider;
import br.com.juliasilva.main.useCase.LoginRegistrationService;
import br.com.juliasilva.main.dto.AuthDTO;


import br.com.juliasilva.main.repository.LoginRepository;

import br.com.juliasilva.main.useCase.ProfileUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cadastro")

public class LoginController {

    @Autowired
    private LoginRegistrationService loginRegistrationService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private LoginRepository loginRepositorio;

    @Autowired
    private ProfileUserService profileUserService;


    @PostMapping("/logar")
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

    public ResponseEntity<Object> auth(@RequestBody AuthUserRequestDTO authUserRequestDTO) {
        try {
            var token = this.loginRegistrationService.execute(authUserRequestDTO);
            return ResponseEntity.ok().body(token);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idUser = request.getAttribute("user_id");
        try {
            var profile = this.profileUserService.execute(UUID.fromString(idUser.toString()));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
