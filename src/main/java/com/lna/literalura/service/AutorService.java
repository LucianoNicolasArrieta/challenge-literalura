package com.lna.literalura.service;

import com.lna.literalura.model.Autor;
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
}
