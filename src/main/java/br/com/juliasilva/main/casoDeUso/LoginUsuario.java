package br.com.juliasilva.main.casoDeUso;


import br.com.juliasilva.main.dto.AutorDTO;
import br.com.juliasilva.main.excecao.EncontradaExcecao;
import br.com.juliasilva.main.repositorio.CadrastroRepositorio;
import br.com.juliasilva.main.repositorio.LoginRepositorio;
import br.com.juliasilva.main.usuario.CadrastroEntidade;
import jakarta.websocket.EncodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class LoginUsuario {

    @Autowired
    private CadrastroRepositorio cadrastroRepositorio;

    @Autowired
    private LoginRepositorio loginRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean execute(AutorDTO autorDTO) {
        return loginRepositorio
                .findByEmail(autorDTO.getEmail())
                .map(login -> passwordEncoder.matches(autorDTO.getSenha(), login.getSenha()))
                .orElseThrow(()-> new RuntimeException("Usuário com email " + autorDTO.getEmail() + " não encontrado."));
    }

}
