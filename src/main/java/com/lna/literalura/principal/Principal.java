package com.lna.literalura.principal;

import com.lna.literalura.model.DatosLibro;
import com.lna.literalura.model.Libro;
import com.lna.literalura.service.AutorService;
import com.lna.literalura.service.ConsumoGutendexAPI;
import com.lna.literalura.service.ConvierteDatos;
import com.lna.literalura.service.LibroService;
import java.util.Scanner;
import java.util.List;

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
                    \n1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarLibro();
                    break;
                case "2":
                    mostrarLibros();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
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
        DatosLibro datosLibro = conversor.obtenerDatos(jsonResultados);

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



}
