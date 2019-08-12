package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Groupe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Groupe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {

}
