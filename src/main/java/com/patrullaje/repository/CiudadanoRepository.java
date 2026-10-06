package com.patrullaje.repository;

/**
 * * * @author Valentina
 */
import com.patrullaje.model.Ciudadano;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadanoRepository extends JpaRepository<Ciudadano, UUID> {

    Optional<Ciudadano> findByDocumento(String documento);
}
