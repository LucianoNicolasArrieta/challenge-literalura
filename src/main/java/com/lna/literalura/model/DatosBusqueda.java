package com.lna.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBusqueda(
                    @JsonAlias("results") ArrayList<Object> encontrados) {
}
