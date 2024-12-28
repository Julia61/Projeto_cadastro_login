package br.com.juliasilva.main.casoDeUso;

import br.com.juliasilva.main.cripto.Seguranca;
import br.com.juliasilva.main.excecao.EncontradaExcecao;
import br.com.juliasilva.main.repositorio.CadrastroRepositorio;
import br.com.juliasilva.main.usuario.CadrastroEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CriarCadrastroUsuario {

    @Autowired
    private CadrastroRepositorio cadrastroRepositorio;

    @Autowired
    private PasswordEncoder passwordEnconter;

    public CadrastroEntidade execute(CadrastroEntidade cadrastroEntidade) {
        this.cadrastroRepositorio
                .findByUsuarioOrEmail(cadrastroEntidade.getUsuario(), cadrastroEntidade.getEmail())
                .ifPresent((user) ->{
                    throw new EncontradaExcecao();
                });

        var senha = passwordEnconter.encode(cadrastroEntidade.getSenha());
        cadrastroEntidade.setSenha(senha);

        return this.cadrastroRepositorio.save(cadrastroEntidade);

    }


}
