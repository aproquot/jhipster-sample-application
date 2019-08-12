package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AssistanceTechnique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssistanceTechnique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssistanceTechniqueRepository extends JpaRepository<AssistanceTechnique, Long> {

}
