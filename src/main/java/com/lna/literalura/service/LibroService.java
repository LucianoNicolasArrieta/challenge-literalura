package com.lna.literalura.service;

import com.lna.literalura.model.Autor;
import com.lna.literalura.model.DatosAutor;
import com.lna.literalura.model.DatosLibro;
import com.lna.literalura.model.Libro;
import com.lna.literalura.repository.AutorRepository;
import com.lna.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public Boolean libroYaExiste(String titulo) {
        return libroRepository.existsByTitulo(titulo);
    }

    @Transactional
    public void crearYGuardarLibro(DatosLibro datosLibro) {

        DatosAutor datosAutor = datosLibro.autores().get(0);
        String[] nombreSeparado = datosAutor.nombreCompleto().split(",");
        String nombre = nombreSeparado[1].trim();
        String apellido = nombreSeparado[0].trim();
        Optional<Autor> autorExistente = autorRepository.findAutor(nombre, apellido, datosAutor.anioDeNacimiento(), datosAutor.anioDeFallecimiento());

        Autor autor = autorExistente.orElseGet(() -> {
           Autor autorNuevo = new Autor(datosAutor);
           return autorRepository.save(autorNuevo);
        });

        Libro libro = new Libro(datosLibro);
        libro.setAutor(autor);

        libroRepository.save(libro);

        System.out.println(libro);
    }

}