package com.lna.literalura.model.autor;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
                    @JsonAlias("name") String nombreCompleto,
                    @JsonAlias("birth_year") int anioDeNacimiento,
                    @JsonAlias("death_year") int anioDeFallecimiento) {
}
