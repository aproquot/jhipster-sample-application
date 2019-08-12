package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TypeAer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeAer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeAerRepository extends JpaRepository<TypeAer, Long> {

}
