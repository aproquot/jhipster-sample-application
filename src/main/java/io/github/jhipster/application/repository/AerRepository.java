package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Aer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Aer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AerRepository extends JpaRepository<Aer, Long> {

}
