package com.lna.literalura.repository;

import com.lna.literalura.model.Autor;
import com.lna.literalura.model.Idioma;
import com.lna.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Boolean existsByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> librosDeAutor(Autor autor);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> librosEnIdioma(Idioma idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.cantidadDescargas DESC LIMIT 10")
    List<Libro> top10Libros();
}
