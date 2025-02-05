package br.com.juliasilva.main.useCase;

import br.com.juliasilva.main.exception.UserException;
import br.com.juliasilva.main.repository.RegisterRepository;
import br.com.juliasilva.main.user.EntityRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserRegistrationService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEnconter;

    public EntityRegister execute(EntityRegister entityRegister) {
        this.registerRepository
                .findByUsuarioOrEmail(entityRegister.getUsuario(), entityRegister.getEmail())
                .ifPresent((user) ->{
                    throw new UserException();
                });

        var senha = passwordEnconter.encode(entityRegister.getSenha());
        entityRegister.setSenha(senha);

        return this.registerRepository.save(entityRegister);

    }


}
