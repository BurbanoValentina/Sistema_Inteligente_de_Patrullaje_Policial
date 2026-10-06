package com.patrullaje.model;

/**
 * * * @author Valentina
 */
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguimiento")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private String comentario;
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private SolicitudAsistencia solicitud;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    private PersonalPolicial personal;

    public Seguimiento() {
        this.fecha = LocalDateTime.now();
    }

    public Seguimiento(String comentario, EstadoSolicitud estado) {
        this.fecha = LocalDateTime.now();
        this.comentario = comentario;
        this.estado = estado;
    }

    @PrePersist
    private void inicializarFecha() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
    }

    public void registrarActualizacion(String comentario, EstadoSolicitud estado) {
        if (comentario == null || comentario.isBlank()) {
            throw new IllegalArgumentException("El comentario es obligatorio.");
        }
        this.comentario = comentario;
        this.estado = estado;
        if (this.fecha == null) {
            this.fecha = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
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

    public SolicitudAsistencia getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudAsistencia solicitud) {
        this.solicitud = solicitud;
    }

    public PersonalPolicial getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalPolicial personal) {
        this.personal = personal;
    }
}
