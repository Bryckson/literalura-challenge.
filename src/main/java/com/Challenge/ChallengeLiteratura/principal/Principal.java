package com.Challenge.ChallengeLiteratura.principal;

import com.Challenge.ChallengeLiteratura.model.DatosResultados;
import com.Challenge.ChallengeLiteratura.service.ConsumoAPI;
import com.Challenge.ChallengeLiteratura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var tituloLibro = teclado.nextLine();

        // 1. PREPARAMOS LA ANIMACIÓN (La rueda)
        Thread hiloAnimacion = new Thread(() -> {
            String[] animacion = {"|", "/", "-", "\\"};
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // \r escribe sobre la misma línea para dar efecto de movimiento
                    System.out.print("\rBuscando... " + animacion[i++ % animacion.length]);
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 2. INICIAMOS LA ANIMACIÓN
        hiloAnimacion.start();

        try {
            // 3. HACEMOS LA LLAMADA A LA API (Mientras la rueda gira)
            var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));

            // 4. DETENEMOS LA ANIMACIÓN CUANDO LLEGAN LOS DATOS
            hiloAnimacion.interrupt();
            hiloAnimacion.join(); // Esperamos a que el hilo muera limpiamente
            System.out.println("\r¡Encontrado!             "); // Limpiamos la línea

            // 5. PROCESAMOS Y MOSTRAMOS LOS DATOS
            var datos = conversor.obtenerDatos(json, DatosResultados.class);
            System.out.println("Top 10 resultados encontrados:");
            datos.resultados().stream()
                    .limit(10)
                    .forEach(System.out::println);

        } catch (Exception e) {
            // Si algo falla, paramos la animación también
            hiloAnimacion.interrupt();
            System.out.println("\nError al buscar: " + e.getMessage());
        }
    }
}