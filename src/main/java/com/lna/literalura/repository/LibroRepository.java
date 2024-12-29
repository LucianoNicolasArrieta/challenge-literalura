package com.lna.literalura.repository;

import com.lna.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Boolean existsByTitulo(String titulo);
}
