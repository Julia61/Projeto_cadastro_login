package br.com.juliasilva.main.casoDeUso;


import br.com.juliasilva.main.repositorio.LoginRepositorio;
import br.com.juliasilva.main.usuario.CadrastroEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUsuario {

    @Autowired
    private LoginRepositorio loginRepositorio;

    public boolean execute(CadrastroEntidade cadrastroEntidade){
        return loginRepositorio
                .findByUsuarioAndEmailAndSenha(cadrastroEntidade.getUsuario(),cadrastroEntidade.getEmail(),cadrastroEntidade.getSenha())
                .isPresent();
    }

}
