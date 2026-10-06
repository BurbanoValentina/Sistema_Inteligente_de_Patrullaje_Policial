package com.patrullaje.dto;

/**
 * * * @author Valentina
 */
import jakarta.validation.constraints.NotBlank;

public class ComunaDTO {

    private Long id;
    @NotBlank(message = "El nombre de la comuna es obligatorio.")
    private String nombre;
    private String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
