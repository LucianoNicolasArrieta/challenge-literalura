package com.lna.literalura.service;

import com.lna.literalura.model.autor.Autor;
import com.lna.literalura.model.autor.DatosAutor;
import com.lna.literalura.model.libro.DatosLibro;
import com.lna.literalura.model.libro.Idioma;
import com.lna.literalura.model.libro.Libro;
import com.lna.literalura.repository.AutorRepository;
import com.lna.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Boolean libroYaExiste(String titulo) {
        return libroRepository.existsByTitulo(titulo);
    }

    @Transactional
    public Libro crearYGuardarLibro(DatosLibro datosLibro) {

        Autor autor =obtenerOGuardarAutorDeLibro(datosLibro);
        Libro libro = new Libro(datosLibro);
        libro.setAutor(autor);

        libroRepository.save(libro);
        return libro;
    }

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public List<Libro> obtenerLibrosPorAutor(Autor autor) {
        return libroRepository.librosDeAutor(autor);
    }

    public List<Libro> obtenerLibrosPorIdioma(Idioma idioma) {
        return libroRepository.librosEnIdioma(idioma);
    }

    public List<Libro> obtenerTop10() {
        return libroRepository.top10Libros();
    }

    private Autor obtenerOGuardarAutorDeLibro(DatosLibro datosLibro) {
        DatosAutor datosAutor = datosLibro.autores().get(0);
        String[] nombreSeparado = datosAutor.nombreCompleto().split(",");
        String nombre = nombreSeparado[1].trim();
        String apellido = nombreSeparado[0].trim();
        Optional<Autor> autorExistente = autorRepository.findAutor(nombre, apellido, datosAutor.anioDeNacimiento(), datosAutor.anioDeFallecimiento());

        Autor autor = autorExistente.orElseGet(() -> {
            Autor autorNuevo = new Autor(datosAutor);
            return autorRepository.save(autorNuevo);
        });

        return autor;
    }
}
