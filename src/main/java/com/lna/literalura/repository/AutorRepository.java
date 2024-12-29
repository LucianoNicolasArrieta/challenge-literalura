package com.lna.literalura.repository;

import com.lna.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Boolean existsByNombreAndApellido(String nombre, String apellido);

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre AND a.apellido = :apellido AND a.anioDeNacimiento = :anioNacimiento AND a.anioDeFallecimiento = :anioFallecimiento")
    Optional<Autor> findAutor(String nombre, String apellido, int anioNacimiento, int anioFallecimiento);

}
