package com.patrullaje.model;

/**
 * * * @author Valentina
 */
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "solicitud_asistencia")
public class SolicitudAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime fechaCreacion;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cai_id")
    private CAI cai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    private PersonalPolicial personalAsignado;
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguimiento> seguimientos = new ArrayList<>();

    public SolicitudAsistencia() {
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    public SolicitudAsistencia(String descripcion, Prioridad prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    @PrePersist
    private void inicializarFechaYEstado() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (estado == null) {
            estado = EstadoSolicitud.PENDIENTE;
        }
    }

    public void cambiarEstado(EstadoSolicitud nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado es obligatorio.");
        }
        if (estado == nuevoEstado) {
            throw new IllegalStateException("La solicitud ya se encuentra en el estado indicado.");
        }
        if (!transicionPermitida(estado, nuevoEstado)) {
            throw new IllegalStateException("Transición no permitida: " + estado + " -> " + nuevoEstado);
        }
        estado = nuevoEstado;
    }

    private boolean transicionPermitida(EstadoSolicitud actual, EstadoSolicitud nuevo) {
        return switch (actual) {
            case PENDIENTE ->
                nuevo == EstadoSolicitud.ASIGNADA || nuevo == EstadoSolicitud.CANCELADA;
            case ASIGNADA ->
                nuevo == EstadoSolicitud.EN_ATENCION || nuevo == EstadoSolicitud.CANCELADA;
            case EN_ATENCION ->
                nuevo == EstadoSolicitud.ATENDIDA || nuevo == EstadoSolicitud.CANCELADA;
            case ATENDIDA, CANCELADA ->
                false;
        };
    }

    public void asignarCai(CAI cai) {
        if (cai == null) {
            throw new IllegalArgumentException("El CAI es obligatorio para la asignación.");
        }
        if (!cai.estaDisponible()) {
            throw new IllegalStateException("El CAI seleccionado no está disponible.");
        }
        this.cai = cai;
    }

    public void agregarSeguimiento(Seguimiento seguimiento) {
        if (seguimiento == null) {
            throw new IllegalArgumentException("El seguimiento no puede ser nulo.");
        }
        seguimiento.setSolicitud(this);
        if (!seguimientos.contains(seguimiento)) {
            seguimientos.add(seguimiento);
        }
    }

    public boolean puedeCancelarse() {
        return estado == EstadoSolicitud.PENDIENTE || estado == EstadoSolicitud.ASIGNADA || estado == EstadoSolicitud.EN_ATENCION;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
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

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public CAI getCai() {
        return cai;
    }

    public void setCai(CAI cai) {
        this.cai = cai;
    }

    public PersonalPolicial getPersonalAsignado() {
        return personalAsignado;
    }

    public void setPersonalAsignado(PersonalPolicial personalAsignado) {
        this.personalAsignado = personalAsignado;
    }

    public List<Seguimiento> getSeguimientos() {
        return seguimientos;
    }
}
