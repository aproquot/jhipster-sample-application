package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FormatDate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FormatDate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormatDateRepository extends JpaRepository<FormatDate, Long> {

}
