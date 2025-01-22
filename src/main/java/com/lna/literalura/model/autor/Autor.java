package com.lna.literalura.model.autor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Integer anioDeNacimiento;
    private Integer anioDeFallecimiento;

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        String[] aux = datosAutor.nombreCompleto().split(",");
        this.nombre = aux[1].trim();
        this.apellido = aux[0].trim();
        this.anioDeNacimiento = datosAutor.anioDeNacimiento();
        this.anioDeFallecimiento = datosAutor.anioDeFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getAnioDeNacimiento() {
        return anioDeNacimiento;
    }

    public void setAnioDeNacimiento(Integer anioDeNacimiento) {
        this.anioDeNacimiento = anioDeNacimiento;
    }

    public Integer getAnioDeFallecimiento() {
        return anioDeFallecimiento;
    }

    public void setAnioDeFallecimiento(Integer anioDeFallecimiento) {
        this.anioDeFallecimiento = anioDeFallecimiento;
    }

    public String nombreCompleto() {
        return apellido + ", " + nombre;
    }

    @Override
    public String toString() {
        return "\n------- AUTOR -------" +
            "\nNombre: " + apellido + ", " + nombre +
            "\nAño de nacimiento: " + anioDeNacimiento +
            "\nAño de fallecimiento: " + anioDeFallecimiento;
    }
}
