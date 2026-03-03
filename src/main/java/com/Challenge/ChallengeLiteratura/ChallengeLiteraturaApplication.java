package com.Challenge.ChallengeLiteratura;

import com.Challenge.ChallengeLiteratura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.muestraElMenu();
    }
}