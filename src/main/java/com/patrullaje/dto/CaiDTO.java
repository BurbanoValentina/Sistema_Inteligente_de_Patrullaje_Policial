package com.patrullaje.dto;

/**
 * * * @author Valentina
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class CaiDTO {

    private Long id;
    @NotBlank(message = "El nombre del CAI es obligatorio.")
    private String nombre;
    @NotBlank(message = "La dirección es obligatoria.")
    private String direccion;
    private String telefono;
    private boolean disponible = true;
    @NotNull(message = "La comuna es obligatoria.")
    private Long comunaId;
    private Set<Long> servicioIds = new HashSet<>();

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getComunaId() {
        return comunaId;
    }

    public void setComunaId(Long comunaId) {
        this.comunaId = comunaId;
    }

    public Set<Long> getServicioIds() {
        return servicioIds;
    }

    public void setServicioIds(Set<Long> servicioIds) {
        this.servicioIds = servicioIds;
    }
}

