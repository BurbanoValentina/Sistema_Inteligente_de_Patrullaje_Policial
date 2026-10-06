package com.patrullaje.repository;

/**
 * * * @author Valentina
 */
import com.patrullaje.model.Seguimiento;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {

    List<Seguimiento> findBySolicitudIdOrderByFechaAsc(UUID solicitudId);
}
