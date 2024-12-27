package com.lna.literalura.principal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lna.literalura.model.DatosBusqueda;
import com.lna.literalura.model.DatosLibro;
import com.lna.literalura.service.ConsumoGutendexAPI;
import com.lna.literalura.service.ConvierteDatos;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoGutendexAPI consumoAPI = new ConsumoGutendexAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() throws JsonProcessingException {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n1 -  Buscar libro por título
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
                    buscaLibro();
//                    String json =
//                    DatosBusqueda resultadoBusqueda = conversor.obtenerDatos(json, DatosBusqueda.class);
//                    System.out.println(resultadoBusqueda);
//                    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
//                    String jsonLibro = objectWriter.writeValueAsString(resultadoBusqueda.encontrados().get(0));
//                    System.out.println(jsonLibro);
//                    DatosLibro libro = conversor.obtenerDatos(jsonLibro, DatosLibro.class);
//                    System.out.println(libro.id() + libro.titulo() + libro.autores() + libro.idiomas() + libro.cantidadDescargas());
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

    private void buscaLibro() {
        System.out.println("Ingrese el libro que desea buscar: ");
        var libro = scanner.nextLine();
        String jsonResultados = consumoAPI.obtenerDatos("https://gutendex.com/books/?search=" + libro);
        DatosBusqueda resultados = conversor.obtenerDatos(jsonResultados, DatosBusqueda.class);
        if (resultados.encontrados().isEmpty()) {
            System.out.println("Libro no encontrado");
        } else {
            System.out.println(resultados.encontrados().get(0));
        }
    }

}
