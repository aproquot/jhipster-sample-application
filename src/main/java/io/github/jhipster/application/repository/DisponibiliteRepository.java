package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Disponibilite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Disponibilite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {

}
