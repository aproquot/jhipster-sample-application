package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.StatutDisponibilite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StatutDisponibilite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatutDisponibiliteRepository extends JpaRepository<StatutDisponibilite, Long> {

}
