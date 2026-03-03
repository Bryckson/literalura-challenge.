package com.Challenge.ChallengeLiteratura;

import com.Challenge.ChallengeLiteratura.principal.Principal;
import com.Challenge.ChallengeLiteratura.repository.AutorRepository;
import com.Challenge.ChallengeLiteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {

    // Inyectamos los repositorios para que Spring nos los pase
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Le pasamos los repositorios a la clase Principal cuando la creamos
        Principal principal = new Principal(libroRepository, autorRepository);
        principal.muestraElMenu();
    }
}