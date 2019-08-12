package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Rv;
import io.github.jhipster.application.repository.RvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Rv}.
 */
@Service
@Transactional
public class RvService {

    private final Logger log = LoggerFactory.getLogger(RvService.class);

    private final RvRepository rvRepository;

    public RvService(RvRepository rvRepository) {
        this.rvRepository = rvRepository;
    }

    /**
     * Save a rv.
     *
     * @param rv the entity to save.
     * @return the persisted entity.
     */
    public Rv save(Rv rv) {
        log.debug("Request to save Rv : {}", rv);
        return rvRepository.save(rv);
    }

    /**
     * Get all the rvs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Rv> findAll(Pageable pageable) {
        log.debug("Request to get all Rvs");
        return rvRepository.findAll(pageable);
    }


    /**
     * Get one rv by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Rv> findOne(Long id) {
        log.debug("Request to get Rv : {}", id);
        return rvRepository.findById(id);
    }

    /**
     * Delete the rv by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Rv : {}", id);
        rvRepository.deleteById(id);
    }
}
