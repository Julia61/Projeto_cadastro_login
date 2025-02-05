package br.com.juliasilva.main.controller;

import br.com.juliasilva.main.useCase.CreateUserRegistrationService;
import br.com.juliasilva.main.user.EntityRegister;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class RegistryController {

    @Autowired
    private CreateUserRegistrationService createUserRegistrationService;

    //Cria o cadastro do usuário
    @PostMapping("/usuario")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Cadastro", description = "Dados do usuário")
    @Operation(summary = "Cadastro de usuário", description = "Essa função é responsável por cadastrar o usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {
                    @Content(schema = @Schema(implementation = EntityRegister.class))
            }),
            @ApiResponse(responseCode = "400",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })

    public ResponseEntity<Object> usuario(@Valid @RequestBody EntityRegister entityRegister){
        try {
            var resultado = this.createUserRegistrationService.execute(entityRegister);
            return ResponseEntity.ok().body(resultado);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
