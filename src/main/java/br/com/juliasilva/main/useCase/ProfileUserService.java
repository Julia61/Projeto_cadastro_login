package br.com.juliasilva.main.useCase;

import br.com.juliasilva.main.dto.UserDTO;
import br.com.juliasilva.main.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileUserService {

    @Autowired
    private RegisterRepository registerRepository;
    public UserDTO execute(UUID idUser) {
        var user = this.registerRepository.findById(idUser)
                .orElseThrow(()-> {
                    throw new UsernameNotFoundException("User not found");
                });

        var userDTO = UserDTO.builder()
                .usuario(user.getUsuario())
                .id(user.getId())
                .build();
        return userDTO;
    }
}
