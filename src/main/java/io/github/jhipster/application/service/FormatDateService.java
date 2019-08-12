package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.FormatDate;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormatDate}.
 */
public interface FormatDateService {

    /**
     * Save a formatDate.
     *
     * @param formatDate the entity to save.
     * @return the persisted entity.
     */
    FormatDate save(FormatDate formatDate);

    /**
     * Get all the formatDates.
     *
     * @return the list of entities.
     */
    List<FormatDate> findAll();


    /**
     * Get the "id" formatDate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormatDate> findOne(Long id);

    /**
     * Delete the "id" formatDate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
