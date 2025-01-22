package br.com.juliasilva.main.cripto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    private static final String[] SWAGGER_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "swagger-resources/**",
            "/actuator/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        https.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/cadastro/usuario").permitAll()
                            .requestMatchers("/cadastro/login").permitAll()
                            .requestMatchers("/user/dados-token").permitAll()
                            .requestMatchers("https://cadastro-login.onrender.com/pagina_cadastro.html").permitAll()
                            .requestMatchers("https://cadastro-login.onrender.com/pagina_login.html").permitAll()
                            .requestMatchers(SWAGGER_LIST).permitAll();

                });

        return https.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
