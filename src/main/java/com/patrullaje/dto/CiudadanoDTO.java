package com.patrullaje.dto;

/**
 * * * @author Valentina
 */
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class CiudadanoDTO {

    private UUID id;
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;
    @NotBlank(message = "El documento es obligatorio.")
    private String documento;
    private String telefono;
    @Email(message = "El correo no tiene un formato válido.")
    private String correo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
