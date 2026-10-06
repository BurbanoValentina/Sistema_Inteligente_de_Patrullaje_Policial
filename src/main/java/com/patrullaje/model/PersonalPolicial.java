package com.patrullaje.model;

/**
 * * * @author Valentina
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_policial")
public class PersonalPolicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(nullable = false, unique = true)
    private String identificacion;
    private String cargo;
    private boolean activo;
    @OneToMany(mappedBy = "personalAsignado")
    private List<SolicitudAsistencia> solicitudesGestionadas = new ArrayList<>();

    public PersonalPolicial() {
        this.activo = true;
    }

    public PersonalPolicial(String nombre, String identificacion, String cargo, boolean activo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.cargo = cargo;
        this.activo = activo;
    }

    public void gestionarSolicitud(SolicitudAsistencia solicitud) {
        if (!activo) {
            throw new IllegalStateException("El personal policial está inactivo.");
        }
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula.");
        }
        solicitud.setPersonalAsignado(this);
        if (!solicitudesGestionadas.contains(solicitud)) {
            solicitudesGestionadas.add(solicitud);
        }
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<SolicitudAsistencia> getSolicitudesGestionadas() {
        return solicitudesGestionadas;
    }
}
