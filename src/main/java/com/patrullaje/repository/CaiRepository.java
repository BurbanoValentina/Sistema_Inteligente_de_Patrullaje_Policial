package com.patrullaje.repository;

/**
 * * * @author Valentina
 */
import com.patrullaje.model.CAI;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaiRepository extends JpaRepository<CAI, Long> {

    List<CAI> findByComunaId(Long comunaId);

    List<CAI> findByDisponibleTrue();
}
