package com.patrullaje.model;

/**
 * * * @author Valentina
 */
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comuna")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "comuna", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CAI> cais = new ArrayList<>();

    public Comuna() {
    }

    public Comuna(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void agregarCai(CAI cai) {
        if (cai == null) {
            throw new IllegalArgumentException("El CAI no puede ser nulo.");
        }
        cai.setComuna(this);
        if (!cais.contains(cai)) {
            cais.add(cai);
        }
    }

    public List<CAI> obtenerCais() {
        return List.copyOf(cais);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CAI> getCais() {
        return cais;
    }
}
