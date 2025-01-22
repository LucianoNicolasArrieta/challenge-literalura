package com.lna.literalura.service;

import com.lna.literalura.model.autor.Autor;
import com.lna.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    public List<Autor> obtenerAutoresVivosEn(int anio) {
        return autorRepository.findAutorVivosEn(anio);
    }

    public List<Autor> obtenerAutoresPorNombre(String nombre) {
        return autorRepository.buscarAutorPorNombre(nombre);
    }
}
