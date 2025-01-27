package br.com.juliasilva.main.useCase;


import br.com.juliasilva.main.dto.AuthDTO;
import br.com.juliasilva.main.exception.EmailNotFoundExceptionOrPasswordWrong;

import br.com.juliasilva.main.repository.RegisterRepository;
import br.com.juliasilva.main.repository.LoginRepository;

import br.com.juliasilva.main.user.EntityRegister;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginRegistrationService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityRegister entityRegister;

    public boolean execute(AuthDTO autorDTO) {

        return loginRepository
                .findByEmail(autorDTO.getEmail())
                .map(login -> passwordEncoder.matches(autorDTO.getSenha(), login.getSenha()))
                .orElse(false);
    }

}
