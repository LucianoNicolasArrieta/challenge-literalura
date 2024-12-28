package com.lna.literalura.model;

import java.util.ArrayList;

public class Libro {
    private Long id;
    private String titulo;
    private ArrayList<Autor> autores;
    private Idioma idioma;
    private Integer cantidadDescargas;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();

        this.autores = new ArrayList<>();
        datosLibro.autores().forEach(datosAutor -> {
            Autor autor = new Autor(datosAutor);
            this.autores.add(autor);
        });
        // Requerimiento de quedarse solo con el primero de los idiomas
        this.idioma = Idioma.valueOf(datosLibro.idiomas().get(0));

        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    @Override
    public String toString() {
        return titulo + " - " + idioma + " - " + cantidadDescargas + " - " + autores;
    }
}
