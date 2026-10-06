package com.patrullaje.model;

/**
 * * * @author Valentina
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cai")
public class CAI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private boolean disponible;
    @ManyToOne(optional = false)
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;
    @ManyToMany
    @JoinTable(name = "cai_servicio", joinColumns = @JoinColumn(name = "cai_id"), inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private Set<ServicioPolicial> servicios = new HashSet<>();

    public CAI() {
        this.disponible = true;
    }

    public CAI(String nombre, String direccion, String telefono, boolean disponible) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.disponible = disponible;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void cambiarDisponibilidad() {
        this.disponible = !this.disponible;
    }

    public void cambiarDisponibilidad(boolean disponible) {
        this.disponible = disponible;
    }

    public void agregarServicio(ServicioPolicial servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo.");
        }
        servicios.add(servicio);
    }

    public void retirarServicio(ServicioPolicial servicio) {
        servicios.remove(servicio);
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

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Set<ServicioPolicial> getServicios() {
        return servicios;
    }
}
