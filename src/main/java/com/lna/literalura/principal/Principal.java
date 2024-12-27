package com.lna.literalura.principal;

import com.lna.literalura.model.DatosLibro;
import com.lna.literalura.service.ConsumoGutendexAPI;
import com.lna.literalura.service.ConvierteDatos;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoGutendexAPI consumoAPI = new ConsumoGutendexAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
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
                    String json = consumoAPI.obtenerDatos("https://gutendex.com/books/1");
                    System.out.println(json);
                    DatosLibro libro = conversor.obtenerDatos(json, DatosLibro.class);
                    System.out.println(libro.id() + libro.titulo() + libro.autores() + libro.idiomas() + libro.cantidadDescargas());
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

}
