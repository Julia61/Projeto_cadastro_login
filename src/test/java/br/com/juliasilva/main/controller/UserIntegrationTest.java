package br.com.juliasilva.main.controller;


import br.com.juliasilva.main.ProjetoCadrastroELoginApplication;
import br.com.juliasilva.main.provider.JWTProvider;
import br.com.juliasilva.main.useCase.LoginRegistrationService;
import br.com.juliasilva.main.dto.AuthDTO;
import br.com.juliasilva.main.repository.RegisterRepository;

import br.com.juliasilva.main.user.EntityRegister;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProjetoCadrastroELoginApplication.class)
@ActiveProfiles("test")
public class UserIntegrationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LoginRegistrationService createLoginRegistrationService;

    @Autowired
    private JWTProvider jwtProvider;


    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_register() throws Exception {

        var register = EntityRegister.builder()
                .usuario("Teste")
                .email("teste@gmail.com")
                .senha("1234567890").build();

        objectMapper.registerModule(new JavaTimeModule());

        String registerJson = objectMapper.writeValueAsString(register);

        mvc.perform(MockMvcRequestBuilders.post("/cadastro/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_not_be_able_to_login_if_no_register() throws Exception {

            var loginRequest = AuthDTO.builder()
                    .email("usuario_inexistente@gmail.com")
                    .senha("senha_invalida").build();

        String loginJson = objectMapper.writeValueAsString(loginRequest);


            mvc.perform(MockMvcRequestBuilders.post("/cadastro/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(loginJson))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }


}
