package com.lna.literalura;

import com.lna.literalura.service.AutorService;
import com.lna.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.lna.literalura.principal.Principal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private AutorService autorService;
	@Autowired
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Principal principal = new Principal(autorService, libroService);
		principal.muestraElMenu();
	}
}
