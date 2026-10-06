package com.patrullaje.dto;

/**
 * * * @author Valentina
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import com.patrullaje.model.EstadoSolicitud;
import com.patrullaje.model.Prioridad;

public class SolicitudAsistenciaDTO {

    private UUID id;
    private LocalDateTime fechaCreacion;
    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;
    private EstadoSolicitud estado;
    @NotNull(message = "La prioridad es obligatoria.")
    private Prioridad prioridad;
    @NotNull(message = "El ciudadano es obligatorio.")
    private UUID ciudadanoId;
    private Long caiId;
    private Long personalId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public UUID getCiudadanoId() {
        return ciudadanoId;
    }

    public void setCiudadanoId(UUID ciudadanoId) {
        this.ciudadanoId = ciudadanoId;
    }

    public Long getCaiId() {
        return caiId;
    }

    public void setCaiId(Long caiId) {
        this.caiId = caiId;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }
}
