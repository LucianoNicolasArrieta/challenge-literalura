package com.lna.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
                    @JsonAlias("id") Long id,
                    @JsonAlias("title") String titulo,
                    @JsonAlias("authors") ArrayList<Object> autores,
                    @JsonAlias("languages") ArrayList<String> idiomas,
                    @JsonAlias("download_count") Integer cantidadDescargas) {
}
