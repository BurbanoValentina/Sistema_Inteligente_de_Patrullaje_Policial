package com.patrullaje.repository;

/**
 * * * @author Valentina
 */
import com.patrullaje.model.PersonalPolicial;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalPolicialRepository extends JpaRepository<PersonalPolicial, Long> {

    Optional<PersonalPolicial> findByIdentificacion(String identificacion);
}
