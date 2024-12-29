package com.lna.literalura.service;

import com.lna.literalura.model.Autor;
import com.lna.literalura.model.DatosAutor;
import com.lna.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Boolean existeAutor(String nombreCompleto) {
        String[] nombreSeparado = nombreCompleto.split(",");
        String nombre = nombreSeparado[1].trim();
        String apellido = nombreSeparado[0].trim();
        return autorRepository.existsByNombreAndApellido(nombre, apellido);
    }

    public Autor obtenerAutor(DatosAutor datosAutor) {
        String[] nombreCompleto = datosAutor.nombreCompleto().split(",");
        String nombre = nombreCompleto[1].trim();
        String apellido = nombreCompleto[0].trim();
        int anioNacimiento = datosAutor.anioDeNacimiento();
        int anioFallecimiento = datosAutor.anioDeFallecimiento();
        Optional<Autor> autor = autorRepository.findAutor(nombre, apellido, anioNacimiento, anioFallecimiento);
        if (autor.isPresent()) {
            System.out.println("El autor ya existe");
            return autor.get();
        } else {
            System.out.println("El autor no existe");
            return new Autor(datosAutor);
        }
    }


}
