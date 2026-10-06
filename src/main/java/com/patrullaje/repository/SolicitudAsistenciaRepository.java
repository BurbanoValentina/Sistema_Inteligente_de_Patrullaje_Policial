package com.patrullaje.repository;

/**
 * * * @author Valentina
 */
import com.patrullaje.model.SolicitudAsistencia;
import com.patrullaje.model.EstadoSolicitud;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudAsistenciaRepository extends JpaRepository<SolicitudAsistencia, UUID> {

    List<SolicitudAsistencia> findByCiudadanoId(UUID ciudadanoId);

    List<SolicitudAsistencia> findByEstado(EstadoSolicitud estado);
}
