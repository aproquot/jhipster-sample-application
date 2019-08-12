package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Statut;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Statut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {

}
