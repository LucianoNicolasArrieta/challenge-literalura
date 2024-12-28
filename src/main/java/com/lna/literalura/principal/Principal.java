package com.lna.literalura.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lna.literalura.model.DatosLibro;
import com.lna.literalura.model.Libro;
import com.lna.literalura.service.ConsumoGutendexAPI;
import com.lna.literalura.service.ConvierteDatos;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoGutendexAPI consumoAPI = new ConsumoGutendexAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private ObjectMapper objectMapper = new ObjectMapper();

    public void muestraElMenu() throws JsonProcessingException {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro obtenerDatosLibro(String libro) {
        String jsonResultados = consumoAPI.obtenerDatos("https://gutendex.com/books/?search=" + libro);
        DatosLibro datosLibro = conversor.obtenerDatos(jsonResultados);

        return datosLibro;
    }

    private void buscarLibro() {
        System.out.println("Ingrese el libro que desea buscar: ");
        var libroBuscado = scanner.nextLine();
        DatosLibro datosLibro = obtenerDatosLibro(libroBuscado);
        if (datosLibro != null) {
            Libro libro = new Libro(datosLibro);
            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado");
        }
    }

}
