package com.lna.literalura.principal;

import com.lna.literalura.model.Autor;
import com.lna.literalura.model.DatosLibro;
import com.lna.literalura.model.Idioma;
import com.lna.literalura.model.Libro;
import com.lna.literalura.service.AutorService;
import com.lna.literalura.service.ConsumoGutendexAPI;
import com.lna.literalura.service.ConvierteDatos;
import com.lna.literalura.service.LibroService;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import java.util.InputMismatchException;
import java.util.IntSummaryStatistics;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoGutendexAPI consumoAPI = new ConsumoGutendexAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private AutorService autorService;
    private LibroService libroService;

    public Principal(AutorService autorService, LibroService libroService) {
        this.autorService = autorService;
        this.libroService = libroService;
    }

    public void muestraElMenu() {
        var opcion = "";
        while (!opcion.equals("0")) {
            var menu = """
                    \n****************** LITERALURA ****************** 
                    \n1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Ver estadisticas de descargas sobre los libros registrados
                    7 - Top 10 libros mas descargados (contando no registrados)
                    8 - Top 10 libros mas descargados (contando solo registrados)
                    
                    0 - Salir
                    ************************************************
                    """;

            System.out.println(menu);
            System.out.println("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarLibro();
                    break;
                case "2":
                    mostrarLibros();
                    break;
                case "3":
                    mostrarAutores();
                    break;
                case "4":
                    mostrarAutoresVivosEnAnio();
                    break;
                case "5":
                    mostrarLibrosPorIdioma();
                    break;
                case "6":
                    mostrarEstadisticas();
                    break;
                case "7":
                    buscarTop10Libros();
                    break;
                case "8":
                    buscarTop10LibrosRegistrados();
                    break;
                case "0":
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro obtenerDatosLibro(String titulo) {
        String jsonResultados = consumoAPI.obtenerDatos("https://gutendex.com/books/?search=" + titulo.replace(" ", "%20"));
        DatosLibro datosLibro = conversor.obtenerDatosLibro(jsonResultados);

        return datosLibro;
    }

    private void buscarLibro() {
        System.out.println("Ingrese el libro que desea buscar: ");
        var libroBuscado = scanner.nextLine();

        System.out.println("Buscando datos sobre '" + libroBuscado + "'...");
        DatosLibro datosLibro = obtenerDatosLibro(libroBuscado);

        if (datosLibro == null) {
            System.out.println("Libro no encontrado.");
        } else if (libroService.libroYaExiste(datosLibro.titulo())) {
            System.out.println("El libro ya se encuentra registrado en el sistema. No se puede registrar el mismo libro mas de una vez.");
        } else {
            libroService.crearYGuardarLibro(datosLibro);
        }
    }

    private void mostrarLibros() {
        List<Libro> libros = libroService.obtenerTodos();
        libros.forEach(System.out::println);
    }

    private void mostrarAutores() {
        List<Autor> autores = autorService.obtenerTodos();
        autores.forEach(autor -> {
            System.out.println(autor +
                "\nLibros: " + libroService.obtenerLibrosPorAutor(autor).stream().map(Libro::getTitulo).toList() +
                "\n----------------------");
        });
    }

    private void mostrarAutoresVivosEnAnio() {
        boolean entradaInvalida = true;
        int anio = 0;
        while (entradaInvalida) {
            try {
                System.out.println("Ingrese el año:");
                anio = scanner.nextInt();
                scanner.nextLine();
                entradaInvalida = false;
            } catch (InputMismatchException e) {
                System.out.println("El año ingresado debe ser un entero. Inténtelo de nuevo.");
                scanner.nextLine();
            }
        }
        List<Autor> autores = autorService.obtenerAutoresVivosEn(anio);
        if (!autores.isEmpty()) {
            autores.forEach(autor -> {
                System.out.println(autor +
                    "\nLibros: " + libroService.obtenerLibrosPorAutor(autor).stream().map(Libro::getTitulo).toList() +
                    "\n----------------------");
            });
        } else {
            System.out.println("No hay autores vivos registrados en el año ingresado.");
        }
    }

    private void mostrarIdiomas() {
        System.out.println("\nIngrese el idioma:" +
            "\nes - Español" +
            "\nen - Inglés" +
            "\npt - Portugués" +
            "\nfr - Francés" +
            "\nit - Italiano");
    }

    private void mostrarLibrosPorIdioma() {
        mostrarIdiomas();
        System.out.println("\nIngrese el idioma que desea buscar: ");
        var idiomaBuscado = scanner.nextLine();

        try {
            Idioma idioma = Idioma.valueOf(idiomaBuscado);
            List<Libro> libros = libroService.obtenerLibrosPorIdioma(idioma);
            if (!libros.isEmpty()) {
                libros.forEach(System.out::println);
            } else {
                System.out.println("No hay libros en el idioma ingresado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("El idioma ingresado no existe.");
        }
    }

    private void mostrarEstadisticas() {
        List<Libro> libros = libroService.obtenerTodos();
        IntSummaryStatistics estadisticas = libros.stream().collect(Collectors.summarizingInt(Libro::getCantidadDescargas));
        System.out.println("------- Estadisticas -------" +
            "\nLas estadisticas fueron generadas sobre los" + estadisticas.getCount() + " libros registrados" +
            "\nCantidad promedio de descargas: " + (int) estadisticas.getAverage() +
            "\nCantidad mas alta de descargas: " + estadisticas.getMax() +
            "\nCantidad mas baja de descargas: " + estadisticas.getMin() +
            "\n----------------------------");

    }

    private List<DatosLibro> obtenerDatosTop10Libros() {
        String jsonResultados = consumoAPI.obtenerDatos("https://gutendex.com/books/?sort=popular");
        List<DatosLibro> datosLibros = conversor.obtenerDatosTop10Libros(jsonResultados);

        return datosLibros;
    }

    private void buscarTop10Libros() {
        System.out.println("Buscando datos sobre el top 10 mas descargados...");
        List<DatosLibro> datosLibros = obtenerDatosTop10Libros();

        for (DatosLibro datosLibro : datosLibros) {
            System.out.println("\n------- LIBRO -------" +
                "\nTítulo: " + datosLibro.titulo() +
                "\nAutor: " + datosLibro.autores().get(0).nombreCompleto() +
                "\nIdioma: " + datosLibro.idiomas().get(0) +
                "\nCantidad de descargas: " + datosLibro.cantidadDescargas() +
                "\n---------------------");
        }
    }

    private void buscarTop10LibrosRegistrados() {
        List<Libro> top10Libros = libroService.obtenerTop10();
        top10Libros.forEach(System.out::println);
    }


}
