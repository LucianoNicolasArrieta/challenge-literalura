package com.lna.literalura.repository;

import com.lna.literalura.model.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Boolean existsByNombreAndApellido(String nombre, String apellido);

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre AND a.apellido = :apellido AND a.anioDeNacimiento = :anioNacimiento AND a.anioDeFallecimiento = :anioFallecimiento")
    Optional<Autor> findAutor(String nombre, String apellido, int anioNacimiento, int anioFallecimiento);

    @Query("SELECT a FROM Autor a WHERE a.anioDeNacimiento <= :anio AND a.anioDeFallecimiento >= :anio")
    List<Autor> findAutorVivosEn(int anio);

    @Query("SELECT a FROM Autor a WHERE " +
        "CONCAT(a.nombre, ' ', a.apellido) ILIKE CONCAT('%', :nombre, '%') OR " +
        "CONCAT(a.apellido, ' ', a.nombre) ILIKE CONCAT('%', :nombre, '%')")
    List<Autor> buscarAutorPorNombre(String nombre);
}
