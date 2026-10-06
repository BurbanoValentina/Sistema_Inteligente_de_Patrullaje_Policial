package com.patrullaje.model;

/**
 * * * @author Valentina
 */
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ciudadano")
public class Ciudadano {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    @Column(nullable = false, unique = true)
    private String documento;
    private String telefono;
    private String correo;
    @OneToMany(mappedBy = "ciudadano", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudAsistencia> solicitudes = new ArrayList<>();

    public Ciudadano() {
    }

    public Ciudadano(String nombre, String documento, String telefono, String correo) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
    }

    public void registrarSolicitud(SolicitudAsistencia solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula.");
        }
        solicitud.setCiudadano(this);
        if (!solicitudes.contains(solicitud)) {
            solicitudes.add(solicitud);
        }
    }

    public List<SolicitudAsistencia> consultarSolicitudes() {
        return List.copyOf(solicitudes);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<SolicitudAsistencia> getSolicitudes() {
        return solicitudes;
    }
}
