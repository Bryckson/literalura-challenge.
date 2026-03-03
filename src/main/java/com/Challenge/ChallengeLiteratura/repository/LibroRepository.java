package com.Challenge.ChallengeLiteratura.repository;

import com.Challenge.ChallengeLiteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Requisito del desafío: Listar libros por idioma
    List<Libro> findByIdioma(String idioma);

    Optional<Libro> findByTituloContains(String titulo);

    List<Libro> findTop10ByOrderByNumeroDeDescargasDesc();
}
