package br.com.juliasilva.main.controller;

import br.com.juliasilva.main.useCase.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/validate-token")
    public ResponseEntity<String> validarToken(HttpServletRequest request){
        return tokenService.validarToken(request);
    }


}
