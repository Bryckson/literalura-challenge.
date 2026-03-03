# LiterAlura Challenge

![Estado del Proyecto](https://img.shields.io/badge/status-finalizado-green)

## 📖 Descripción del Proyecto

LiterAlura es una aplicación de consola desarrollada en Java que permite a los usuarios interactuar con un catálogo de libros. La aplicación consume la API de Gutendex para buscar libros por título, los registra en una base de datos PostgreSQL y permite realizar diversas consultas sobre los datos almacenados. Este proyecto fue desarrollado como parte del Challenge de Alura Latam en conjunto con Oracle (ONE).

## 🚀 Funcionalidades

El programa ofrece un menú interactivo con las siguientes opciones:

1.  **Buscar libro por título:** Consulta la API de Gutendex, obtiene la información del libro y lo guarda en la base de datos local, evitando duplicados.
2.  **Listar libros registrados:** Muestra todos los libros guardados en la base de datos.
3.  **Listar autores registrados:** Muestra todos los autores de los libros guardados, sin duplicados.
4.  **Listar autores vivos en un determinado año:** Pide un año al usuario y muestra qué autores registrados estaban vivos en esa fecha.
5.  **Listar libros por idioma:** Permite buscar y mostrar todos los libros registrados en un idioma específico (ej: `es`, `en`, `fr`).
6.  **Top 10 libros más descargados:** Muestra una lista de los 10 libros más populares según su número de descargas.

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA:** Para la persistencia y consultas a la base de datos.
- **PostgreSQL:** Como sistema de gestión de base de datos.
- **Maven:** Para la gestión de dependencias del proyecto.
- **API Gutendex:** Como fuente externa de datos de libros.

## ⚙️ Cómo ejecutar el proyecto

1.  Clonar el repositorio:
    ```bash
    git clone https://github.com/Bryckson/literalura-challenge.git
    ```
2.  Configurar la base de datos en `src/main/resources/application.properties`, asegurando que la URL, el usuario y la contraseña de PostgreSQL sean correctos.
3.  Ejecutar el proyecto desde su IDE de preferencia. La aplicación se iniciará y mostrará el menú en la consola.

## 👤 Autor

**Bryckson Gutierrez** - [Mi Perfil de LinkedIn](https://www.linkedin.com/in/bryckson-gutierrez)