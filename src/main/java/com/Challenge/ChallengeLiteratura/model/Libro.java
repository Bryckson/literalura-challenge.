package com.Challenge.ChallengeLiteratura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Double numeroDeDescargas;

    @ManyToOne
    private Autor autor;

    // Constructor vacío
    public Libro() {}

    // Constructor inteligente: Recibe los datos de la API y el Autor ya procesado
    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        // La API devuelve una lista de idiomas, nos quedamos con el primero
        this.idioma = !datosLibro.idiomas().isEmpty() ? datosLibro.idiomas().get(0) : null;
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "------ LIBRO ------\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNombre() : "Desconocido") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Descargas: " + numeroDeDescargas + "\n" +
                "-------------------";
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Double getNumeroDeDescargas() { return numeroDeDescargas; }
    public void setNumeroDeDescargas(Double numeroDeDescargas) { this.numeroDeDescargas = numeroDeDescargas; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}