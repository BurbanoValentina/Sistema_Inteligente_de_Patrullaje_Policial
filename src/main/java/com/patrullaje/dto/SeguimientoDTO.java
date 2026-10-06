package com.patrullaje.dto;

/**
 * * * @author Valentina
 */
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;
import com.patrullaje.model.EstadoSolicitud;

public class SeguimientoDTO {

    private Long id;
    private LocalDateTime fecha;
    @NotBlank(message = "El comentario es obligatorio.")
    private String comentario;
    private EstadoSolicitud estado;
    private UUID solicitudId;
    private Long personalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public UUID getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(UUID solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }
}
