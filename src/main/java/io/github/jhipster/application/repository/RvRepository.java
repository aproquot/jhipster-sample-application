package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Rv;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Rv entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvRepository extends JpaRepository<Rv, Long> {

}
