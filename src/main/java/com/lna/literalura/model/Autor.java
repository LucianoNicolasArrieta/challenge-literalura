package com.lna.literalura.model;

public class Autor {
    private Long id;
    private String nombreCompleto;
    private Integer anioDeNacimiento;
    private Integer anioDeFallecimiento;

    public Autor(DatosAutor datosAutor) {
        this.nombreCompleto = datosAutor.nombreCompleto();
        this.anioDeNacimiento = datosAutor.anioDeNacimiento();
        this.anioDeFallecimiento = datosAutor.anioDeFallecimiento();
    }

    @Override
    public String toString() {
        return nombreCompleto + "-" + anioDeNacimiento + "-" + anioDeFallecimiento;
    }
}
