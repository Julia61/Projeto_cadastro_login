package br.com.juliasilva.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://projeto-cadastro-login.onrender.com")
                        .allowedOrigins("https://cadastro-login.onrender.com")
                        .allowedOrigins("https://cadastro-login.onrender.com/pagina_cadastro.html")
                        .allowedOrigins("https://cadastro-login.onrender.com/pagina_login.html")

                        .allowedOrigins("http://localhost:63342")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}