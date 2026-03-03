package com.Challenge.ChallengeLiteratura.repository;

import com.Challenge.ChallengeLiteratura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Para no repetir autores
    Optional<Autor> findByNombre(String nombre);

    // Requisito del desafío: Listar autores vivos en un año determinado
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeFallecimiento > :anio OR a.fechaDeFallecimiento IS NULL)")
    List<Autor> autoresVivosEnDeterminadoAnio(int anio);
}