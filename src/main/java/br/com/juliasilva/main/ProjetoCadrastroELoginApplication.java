package br.com.juliasilva.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.juliasilva.main")

public class ProjetoCadrastroELoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCadrastroELoginApplication.class, args);
	}

}

