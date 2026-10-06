package com.patrullaje.dto;

/**
 * * * @author Valentina
 */
import jakarta.validation.constraints.NotBlank;

public class ServicioPolicialDTO {

    private Long id;
    @NotBlank(message = "El nombre del servicio es obligatorio.")
    private String nombre;
    private String descripcion;
    private boolean activo = true;

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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
