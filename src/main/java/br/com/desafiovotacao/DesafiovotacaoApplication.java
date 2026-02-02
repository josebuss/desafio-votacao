package br.com.desafiovotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DesafiovotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiovotacaoApplication.class, args);
	}

}
