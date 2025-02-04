package com.lna.literalura.model.libro;

import com.lna.literalura.model.autor.Autor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer cantidadDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();

        // Requerimiento de quedarse solo con el primer autor
        //this.autor = new Autor(datosLibro.autores().get(0));
        
        // Requerimiento de quedarse solo con el primero de los idiomas
        this.idioma = Idioma.valueOf(datosLibro.idiomas().get(0));

        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "\n------- LIBRO -------" +
            "\nTítulo: " + titulo +
            "\nAutor: " + autor.nombreCompleto() +
            "\nIdioma: " + idioma +
            "\nCantidad de descargas: " + cantidadDescargas +
            "\n---------------------";
    }
}
