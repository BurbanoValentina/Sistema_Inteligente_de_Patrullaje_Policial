package com.patrullaje.repository;

/**
 * * * @author Valentina
 */
import com.patrullaje.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunaRepository extends JpaRepository<Comuna, Long> {
}
