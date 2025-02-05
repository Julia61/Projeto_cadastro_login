package br.com.juliasilva.main.cripto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class Security {

    @Autowired
    private SecurityUserFilter securityUserFilter;

    private static final String[] PERMIT_ALL_LIST = {
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
                            .requestMatchers("/cadastro/logar").permitAll()
                            .requestMatchers("/user/dados-token").permitAll()
                            .requestMatchers(PERMIT_ALL_LIST).permitAll()
                            .requestMatchers("/**").permitAll();
                            auth.anyRequest().authenticated();


                })
                .addFilterBefore(securityUserFilter, BasicAuthenticationFilter.class);



        return https.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
