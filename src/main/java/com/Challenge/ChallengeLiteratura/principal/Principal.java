package com.Challenge.ChallengeLiteratura.principal;

import com.Challenge.ChallengeLiteratura.model.*;
import com.Challenge.ChallengeLiteratura.repository.AutorRepository;
import com.Challenge.ChallengeLiteratura.repository.LibroRepository;
import com.Challenge.ChallengeLiteratura.service.ConsumoAPI;
import com.Challenge.ChallengeLiteratura.service.ConvierteDatos;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";

    // Repositorios para la base de datos
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    // Libros y Autores cacheados para no consultar la BD a cada rato
    private List<Libro> libros;
    private List<Autor> autores;

    // El constructor ahora recibe los repositorios
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \nElija la opción a través de su número:
                    1 - Buscar libro por título y guardarlo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Top 10 libros más descargados
                    
                    0 - Salir
                    """;
            System.out.println(menu);

            // Bloque try-catch para evitar que el programa explote si el usuario escribe letras
            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("¡Opción inválida! Por favor, ingrese un número válido del menú.");
                continue; // Salta el resto del código y vuelve a mostrar el menú
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    top10LibrosMasDescargados();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente nuevamente.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosResultados.class);

        // Buscamos el primer libro de la lista de resultados
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibro datosLibro = libroBuscado.get();
            // Lógica para no guardar libros duplicados
            if (libroRepository.findByTituloContains(datosLibro.titulo()).isPresent()) {
                System.out.println("\nEl libro '" + datosLibro.titulo() + "' ya está registrado.");
                return;
            }

            // Lógica para no guardar autores duplicados
            DatosAutor datosAutor = datosLibro.autores().get(0);
            Optional<Autor> autorExistente = autorRepository.findByNombre(datosAutor.nombre());
            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                autor = new Autor(datosAutor);
                autorRepository.save(autor);
            }

            Libro libro = new Libro(datosLibro, autor);
            libroRepository.save(libro);
            System.out.println("\nLibro guardado exitosamente:");
            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosPorAnio() {
        System.out.println("Introduce el año para buscar autores vivos:");
        var anio = teclado.nextInt();
        teclado.nextLine();
        autores = autorRepository.autoresVivosEnDeterminadoAnio(anio);
        if (autores.isEmpty()){
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Escribe el código del idioma para la búsqueda (ej: es, en, fr, pt):");
        var idioma = teclado.nextLine();
        libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()){
            System.out.println("No se encontraron libros en ese idioma.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void top10LibrosMasDescargados() {
        libros = libroRepository.findTop10ByOrderByNumeroDeDescargasDesc();
        System.out.println("\n--- Top 10 Libros Más Descargados ---");
        libros.forEach(System.out::println);
    }
}